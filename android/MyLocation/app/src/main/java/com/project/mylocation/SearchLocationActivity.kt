package com.project.mylocation

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.location.LocationServices
import com.project.mylocation.adpater.AddressListAdapter
import com.project.mylocation.helper.CheckPermission
import com.project.mylocation.helper.KakaoLocalAPI
import com.project.mylocation.model.ResultGetLocation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class SearchLocationActivity : AppCompatActivity(), AbsListView.OnScrollListener {

    private val API_KEY = "KakaoAK 653c5e70cbebeedc0385358c03eeb1c9"
    private val BASE_URL_KAKAO_API = "https://dapi.kakao.com/"
    private val TAG: String = "[SearchLocationActivity]"
    private val checkPermission = CheckPermission(this@SearchLocationActivity)
    private var callingActivity: Int = 1

    private lateinit var addressSet: MutableSet<String>
    private lateinit var tempAddressSet: MutableSet<String>

    private lateinit var addressAdapter: AddressListAdapter
    private lateinit var addressListView: ListView
    private lateinit var addressList: ArrayList<String>

    private var lastItemVisibleFlag = false
    private var PAGE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_location)

        addressListView = findViewById(R.id.addressListView)

        // 변수 초기화
        addressSet = mutableSetOf()
        tempAddressSet = mutableSetOf()
        addressList = ArrayList()
        addressAdapter = AddressListAdapter(this, addressList)
        addressListView.adapter = addressAdapter

        // 뒤로가기
        findViewById<Button>(R.id.backButton).setOnClickListener {
            finish()
        }

        // 현위치로 찾기(처음 부터)
        findViewById<Button>(R.id.searchLocationButton).setOnClickListener {
            addressSet = mutableSetOf()
            tempAddressSet = mutableSetOf()
            addressList = ArrayList()
            addressAdapter = AddressListAdapter(this, addressList)
            addressListView.adapter = addressAdapter
            PAGE = 1
            getLocation()
        }
        
        // 스크롤 이벤트
        findViewById<ListView>(R.id.addressListView).setOnScrollListener(this)

        getLocation()
    }

    private fun getLocation(){
        var fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        if(checkPermission.checkPermission()){
            Log.e("MAIN", "권한 체크 됨")
            Log.e("MAIN", "PAGE : $PAGE")
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        val x = location.longitude
                        val y = location.latitude

                        // RestFulAPI를 가져오기
                        val retrofit = Retrofit.Builder()
                            .baseUrl(BASE_URL_KAKAO_API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        val api = retrofit.create(KakaoLocalAPI::class.java)
                        val callGetLocalByCategory = api.getLocalByCategory(
                            API_KEY,
                            "PO3",
                            x,
                            y,
                            10000,
                            PAGE,
                            15,
                            "distance"
                        )

                        callGetLocalByCategory.enqueue(object : Callback<ResultGetLocation> {
                            override fun onResponse(
                                call: Call<ResultGetLocation>,
                                response: Response<ResultGetLocation>
                            ) {
                                Log.d(TAG, "onResponse: 성공")
                                // 숫자도 표시해야함 ex) 수내1동
                                for (i in response.body()!!.documents) {
                                    addressSet.add(
                                        i.address_name.substring(
                                            0, i.address_name.lastIndexOf(
                                                " "
                                            )
                                        )
                                    )
                                    // addressSet.add((i.address_name).replace(("[^가-힣\\s]").toRegex(),"").trim())
                                }
                                addressList.addAll(addressSet subtract tempAddressSet)
                                tempAddressSet.addAll(addressSet)
                                addressAdapter.notifyDataSetChanged()
                                setAdapter()
                                PAGE++

                                // 데이터 추가용
//                                if(addressList.size < 12 && PAGE < 10){
//                                    getLocation()
//                                }
                            }

                            override fun onFailure(call: Call<ResultGetLocation>, t: Throwable) {
                                Log.d(TAG, "onFailure: 실패")
                            }
                        })
                    }
                }
        }

    }

    private fun setAdapter() {
        addressListView.setOnItemClickListener { parent, view, position, id ->
            // 리스트에서 주소 클릭했을때 액티비티로 돌아가면서 주소 세팅
            if(callingActivity == 1){
                // 프로필에서 주소 설정
                var intent = Intent(this, MainActivity::class.java)
            } else if(callingActivity == 2){
                // 회원가입할때
                // var intent = Intent(this, MainActivity::class.java)
            }
            intent.putExtra("fullAddress", addressAdapter.getItem(position).toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
        // 1. OnScrollListener.SCROLL_STATE_IDLE : 스크롤이 이동하지 않을때의 이벤트(즉 스크롤이 멈추었을때).
        // 2. lastItemVisibleFlag : 리스트뷰의 마지막 셀의 끝에 스크롤이 이동했을때.
        // 3. mLockListView == false : 데이터 리스트에 다음 데이터를 불러오는 작업이 끝났을때.
        // && mLockListView == false
        // 1, 2, 3 모두가 true일때 다음 데이터를 불러온다.
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag ) {
            // 화면이 바닦에 닿을때 처리
            // 로딩중을 알리는 프로그레스바를 보인다.
            // 다음 데이터를 불러온다.
            getLocation()
        }
    }
    override fun onScroll(
        view: AbsListView?,
        firstVisibleItem: Int,
        visibleItemCount: Int,
        totalItemCount: Int
    ) {
        // firstVisibleItem : 화면에 보이는 첫번째 리스트의 아이템 번호.
        // visibleItemCount : 화면에 보이는 리스트 아이템의 갯수
        // totalItemCount : 리스트 전체의 총 갯수
        // 리스트의 갯수가 0개 이상이고, 화면에 보이는 맨 하단까지의 아이템 갯수가 총 갯수보다 크거나 같을때.. 즉 리스트의 끝일때. true
        lastItemVisibleFlag = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount);
    }

}