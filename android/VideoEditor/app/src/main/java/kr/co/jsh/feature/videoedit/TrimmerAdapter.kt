package kr.co.jsh.feature.videoedit

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.co.jsh.customview.TimeLineView
import kr.co.jsh.databinding.ItemVideoTimelineListBinding

class TrimmerAdapter (private var thumbnailList : ArrayList<ArrayList<Bitmap>>, private var context: Context)
    : RecyclerView.Adapter<TrimmerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val mBinding = ItemVideoTimelineListBinding.inflate(inflater, parent, false)
        return ViewHolder(mBinding, mBinding.itemVideo)
    }

    override fun getItemCount(): Int = thumbnailList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image.drawView(thumbnailList[position])
    }


    inner class ViewHolder(
    mBinding: ItemVideoTimelineListBinding,
    val image : TimeLineView
    ) : RecyclerView.ViewHolder(mBinding.root)
}