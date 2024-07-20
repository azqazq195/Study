package kr.co.jsh.feature.videoedit

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaExtractor
import android.media.MediaFormat
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.util.Log
import android.widget.Toast
import android.widget.VideoView
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import kr.co.domain.api.usecase.PostFileUploadUseCase
import kr.co.domain.api.usecase.PostImproveVideoPidNumber
import kr.co.domain.api.usecase.PostVideoPidNumberAndInfoUseCase
import kr.co.domain.globalconst.Consts
import kr.co.domain.globalconst.Consts.Companion.EXTRA_VIDEO_PATH
import kr.co.domain.globalconst.PidClass
import kr.co.domain.utils.addFile
import kr.co.domain.utils.toastShort
import kr.co.jsh.singleton.UserObject
import kr.co.jsh.utils.*
import kr.co.jsh.utils.BitmapUtil.bitmapToFileUtil
import kr.co.jsh.utils.permission.RealPathUtil
import kr.co.jsh.utils.permission.ScopeStorageFileUtil
import kr.co.jsh.utils.videoUtil.TrimVideoUtils
import kr.co.jsh.utils.videoUtil.VideoOptions
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TrimmerPresenter(override var view: TrimmerContract.View,
                       private var postFileUploadUseCase: PostFileUploadUseCase,
                       private var postPidNumberAndInfoUseCase: PostVideoPidNumberAndInfoUseCase,
                       private var postImproveVideoPidNumber: PostImproveVideoPidNumber) : TrimmerContract.Presenter{

    private var mplayer: SimpleExoPlayer ?= null
    private var playbackPosition = 0L
    private var currentWindow = 0 //재생곡의 순번
    private var playWhenReady = false
    private val mediaMetadataRetriever = MediaMetadataRetriever()

    private val originalTrimList = ArrayList<Pair<Long, Long>>()


    override fun initPlayer(uri: Uri, context: Context) {
        mplayer = SimpleExoPlayer.Builder(context).build()
        val dataSourceFactory =
            DefaultDataSourceFactory(context ,"del.it")
        val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
        mplayer!!.apply{
            prepare(mediaSource)
            seekTo(currentWindow, playbackPosition)
            playWhenReady = playWhenReady
            view.setPlayer(mplayer!!)
        }

        mediaMetadataRetriever.setDataSource(context, uri)
    }

    override fun getVideoListener() {
        mplayer?.addListener(object: Player.EventListener{
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                if (playbackState == ExoPlayer.STATE_READY) {
                    val realDurationMillis: Long = mplayer?.duration!!
                    view.setVideoDuration(realDurationMillis)
                } else if(playbackState == ExoPlayer.STATE_ENDED){
                    mplayer!!.seekTo(0)
                    view.onVideoFinished()
                }
                super.onPlayerStateChanged(playWhenReady, playbackState)
            }
        })
    }

    override fun releasePlayer() {
        mplayer?.let{
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            playWhenReady = it.playWhenReady
            it.release()
            mplayer = null
        }
    }

    override fun isVideoPlay(whenReady: Boolean) {
        mplayer?.playWhenReady = whenReady
        view.setVideoPlayFlag(whenReady)
    }

    override fun getVideoCurrentPosition(): Float {
        return mplayer?.currentPosition!!.toFloat()
    }

    override fun setVideoSeekTo(currentPosition: Long) {
        mplayer?.seekTo(currentPosition)
    }

    override fun getFrameBitmap(sec: Long) {
        val bitmap
                = mediaMetadataRetriever.getFrameAtTime(sec * 1000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
        view.setDrawBitmap(bitmap)
    }

    //----------

    override fun setCuttingVideo(context: Context, trimVideoTimeList: ArrayList<Pair<Long, Long>>, recycler: RecyclerView){
        val trimmedPosition = ( mplayer?.currentPosition!! * (recycler.width - ScreenSizeUtil(context).widthPixels)) /  mplayer?.duration!!

        trimVideoTimeList.add(Pair(trimmedPosition, mplayer?.currentPosition!!))// 자를때마다 계속 들어가게 됨.
        originalTrimList.add(Pair(trimmedPosition, mplayer?.currentPosition!!)) //undo redo 시 사용
        trimVideoTimeList.sortBy { it.first } //desc? asc?

        view.setGreyLine(trimVideoTimeList, trimmedPosition)
    }

    override fun resetTrimVideoLIst() {
        originalTrimList.clear()
    }

    override fun getIndexOfTrimVideoList(index : Int): Pair<Long, Long> {
        return originalTrimList[originalTrimList.lastIndex - index]
    }

    //사용자가 자른 동영상이 갤러리와 서버 동시에 저장, 업로드 되는 메소드
    override fun getResultUri(uri: Uri, context: Context, option: String) {
        ScopeStorageFileUtil.addVideoAlbum(uri, context)

        if(option == Consts.SUPER_RESOL) { improveFile(uri.toString()) }
        else { uploadFile(uri.toString(), Consts.DEL_OBJ) }
    }

    override fun preparePath(extraIntent: Intent) {
        extraIntent.let{
            val path =  it.getStringExtra(EXTRA_VIDEO_PATH)
            path }.let{ view.setVideoPath(it)}
    }

    override fun getThumbnailList(mSrc: Uri, context:Context) {
        val thumbnailList = ArrayList<Bitmap>()
        val mediaMetadataRetriever = MediaMetadataRetriever()
        mediaMetadataRetriever.setDataSource(context, mSrc)

        val videoLengthInMs = (Integer.parseInt(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION))).toLong()
        val cropHeight = 150
        val cropWidth = ScreenSizeUtil(context).widthPixels/4 //timelineview에서 한 프레임의 너비
        val interval = if(videoLengthInMs< 3000) videoLengthInMs else 3000


        for (i in 0 .. videoLengthInMs step interval) {
            var bitmap = mediaMetadataRetriever.getFrameAtTime(i * 1000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC)
            bitmap?.let{
                bitmap = Bitmap.createScaledBitmap(bitmap, cropWidth, cropHeight, false)
                Timber.i("${bitmap.width}, ${bitmap.height}")
            }
            thumbnailList.add(bitmap)
            Timber.i("ArrayList Size is ${thumbnailList.size}")

        }
        mediaMetadataRetriever.release()
        view.setThumbnailListView(thumbnailList)
    }

    override fun trimVideo(path: String, context:Context, mSrc: Uri,  start_sec: Int, end_sec: Int) {
        val mediaMetadataRetriever = MediaMetadataRetriever()
        mediaMetadataRetriever.setDataSource(context, mSrc)

        val file = File(mSrc.path ?: "")
        val extractor = MediaExtractor()
        var frameRate = 24
        try {
            extractor.setDataSource(file.path)
            val numTracks = extractor.trackCount
            for (i in 0..numTracks) {
                val format = extractor.getTrackFormat(i)
                val mime = format.getString(MediaFormat.KEY_MIME)
                if (mime.startsWith("video/")) {
                    if (format.containsKey(MediaFormat.KEY_FRAME_RATE)) {
                        frameRate = format.getInteger(MediaFormat.KEY_FRAME_RATE)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            extractor.release()
        }
        val duration = java.lang.Long.parseLong(mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION))
        Timber.d("FRAME RATE: $frameRate")
        Timber.d("FRAME COUNT: ${(duration / 1000 * frameRate)}")
        VideoOptions(context)
            .trimVideo(TrimVideoUtils.stringForTime(start_sec.toFloat()), TrimVideoUtils.stringForTime(end_sec.toFloat()), file.path, view)
    }


    //Todo 동영상과 사진 확장자를 업로드 할 수 있는 메소드
    @SuppressLint("CheckResult")
    override fun uploadFile(uri: String, type: String) {
        //val path = "file://" + Uri.parse(uri)
        val path = uri.addFile()
        val request = MultipartBody.Part.createFormData("file", path, RequestBody.create(MediaType.parse("video/*"), Uri.parse(path).toFile() ))
        postFileUploadUseCase.postFile(request)
            .subscribe({
                if(it.status.toInt() == 200 )
                {
                    UserObject.ResponseCode = it.status.toInt()
                    view.uploadSuccess(it.message)
                    PidClass.videoObjectPid = it.datas.objectPid
                    if(type == Consts.SUPER_RESOL) improveFile(uri)
                }
                else {
                    view.uploadFailed(it.message)
                    //PidClass.videoObjectPid = it.datas.objectPid
                }
            },{
                view.uploadFailed(it.localizedMessage)
            })
    }

    @SuppressLint("CheckResult")
    override fun uploadMaskFile(bitmap: Bitmap, frameTimeSec:Float, context: Context) {
        val file = bitmapToFileUtil(bitmap, context)
        val path = file.toString().addFile()
        val request = MultipartBody.Part.createFormData("file", path , RequestBody.create(MediaType.parse("image/*"),Uri.parse(path).toFile()))
        postFileUploadUseCase.postFile(request)
            .subscribe({
                if(it.status.toInt() == 200 ) {
                    PidClass.videoMaskObjectPid = it.datas.objectPid
                    sendVideoResultToServerWithInfo(PidClass.videoMaskObjectPid, frameTimeSec, PidClass.videoObjectPid)
                }
                else view.uploadFailed(it.message)
            },{
                view.uploadFailed("로그인 후 가능")
                view.cancelJob()

            })
    }

    @SuppressLint("CheckResult")
    private fun improveFile(uri: String){
        val path = uri.addFile()
        val request = MultipartBody.Part.createFormData("file", path, RequestBody.create(MediaType.parse("video/*"), Uri.parse(path).toFile() ))
        postFileUploadUseCase.postFile(request)
            .subscribe({
                if(it.status.toInt() == 200 ) {
                    PidClass.videoObjectPid = it.datas.objectPid
                    requestImproveVideo(PidClass.videoObjectPid)
                }
                else view.uploadFailed(it.message)
            },{
                view.uploadFailed("로그인 후 가능")
                view.cancelJob()

            })
    }


    @SuppressLint("CheckResult", "SimpleDateFormat")
    private fun sendVideoResultToServerWithInfo(maskPid: String, frameSec: Float, videoPid: String) {
        val time = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
        val curTime = dateFormat.format(Date(time))

        postPidNumberAndInfoUseCase.postPidNumberAndInfo(maskPid, frameSec/1000f, Consts.DEL_OBJ ,videoPid, curTime)
            .subscribe({
                if(it.status.toInt() == 200) {
                    Timber.d("Complete Video Remove Request")
                    PidClass.topVideoObjectPid.add(it.datas.objectPid)
                    view.stopAnimation()

                }
                else Timber.e("ERROR ${it.status}")
            },{
                it.localizedMessage
            })
    }

    @SuppressLint("CheckResult", "SimpleDateFormat")
    private fun requestImproveVideo(videoPid:String){
        val time = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val curTime = dateFormat.format(Date(time))

        postImproveVideoPidNumber.postImproveVideoPidNumber(Consts.SUPER_RESOL, videoPid, curTime)
            .subscribe({
                if(it.status.toInt() == 200) {
                    Timber.d("Complete Video Improve Request")
                    view.stopAnimation()
                }
                else Timber.e("ERROR ${it.status}")
            },{
                it.localizedMessage
            })
    }
}