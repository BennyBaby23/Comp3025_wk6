package com.mdev.comp3025_wk6

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contactModel: ContactModel = ContactModel("Benny baby", "39284729874", "benoydudsu@gmail.com")

        Log.i("contact_model", contactModel.toString())

        val contactList = deserializeJSON()
        val contactList2 = deserializeJSON2()
        if (contactList != null)
        {
            for(contact in contactList)
            {
                Log.i("listItem", contact.toString())
            }
        }
        if (contactList2 != null)
        {
            for(contact in contactList2)
            {
                Log.i("listItem", contact.toString())
            }
        }



    }


    fun getTextFromResource(context: Context, resourceId: Int): String
    {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use {it.readText()}
    }

    fun getTextFromAssets(context: Context, fileName: String): String
    {
        return context.resources.assets.open(fileName)
            .bufferedReader()
            .use {it.readText()}
    }

    fun deserializeJSON(): List<ContactModel>?
    {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, ContactModel::class.java)
        val adapter: JsonAdapter<List<ContactModel>> = moshi.adapter(listType)

        val contactListRawString = getTextFromResource(this, R.raw.contact)
        val contactList: List<ContactModel>? = adapter.fromJson(contactListRawString)
        return contactList
    }

    fun deserializeJSON2(): List<ContactModel>?
    {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val listType = Types.newParameterizedType(List::class.java, ContactModel::class.java)
        val adapter: JsonAdapter<List<ContactModel>> = moshi.adapter(listType)

        val contactListRawString = getTextFromAssets(this, "contact.json")
        val contactList2: List<ContactModel>? = adapter.fromJson(contactListRawString)
        return contactList2
    }

}