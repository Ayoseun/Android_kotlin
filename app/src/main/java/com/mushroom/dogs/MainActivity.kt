package com.mushroom.dogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.StringRequestListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mushroom.dogs.adapter.DogsAdapter
import com.mushroom.dogs.model.DogsApi
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
     val imageList = ArrayList<DogsApi>()
    private lateinit var dogsRv : RecyclerView
    private lateinit var searchbtn: FloatingActionButton
    private lateinit var dogNameText:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogsRv = findViewById<RecyclerView>(R.id.dogsRv)
        searchbtn = findViewById<FloatingActionButton>(R.id.searchbtn)
        dogNameText = findViewById<EditText>(R.id.dogNameText)


        dogsRv.layoutManager = StaggeredGridLayoutManager(2,LinearLayoutManager.HORIZONTAL)

        searchbtn.setOnClickListener{
            var name = dogNameText.text.toString()

            searchdogs(name)
        }
    }

    private fun searchdogs(name: String) {

        imageList.clear()
        AndroidNetworking.get("https://dog.ceo/api/breed/$name/images")
            .setPriority(Priority.HIGH)
            .build()
            .getAsString(object: StringRequestListener{
                override fun onResponse(response: String?) {
                    val result = JSONObject(response)
                    val image = result.getJSONArray("message")

                    for( i in 0 until image.length()){
                       val gist = image.get(i)
                        imageList.add(DogsApi(gist.toString()))
                    }
                   dogsRv.adapter = DogsAdapter(this@MainActivity, imageList)
                }

                override fun onError(anError: ANError?) {

                }

            })
    }
}