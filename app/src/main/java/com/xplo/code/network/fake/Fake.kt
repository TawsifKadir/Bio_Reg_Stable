package com.xplo.code.network.fake
import android.annotation.SuppressLint
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
//import com.fasterxml.jackson.databind.ObjectMapper
import com.kit.integrationmanager.model.Beneficiary
import com.xplo.code.R
import com.xplo.code.core.Contextor
import com.xplo.code.data_module.model.user.User
import com.xplo.code.network.model.IbdbModel
import com.xplo.code.utils.JsonUtils
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import kotlin.random.Random

/**
 * Copyright 2020 (C) xplo
 *
 * Created  : 3/16/20
 * Updated  :
 * Author   : xplo
 * Desc     :
 * Comment  :
 */

@SuppressLint("StaticFieldLeak")
object Fake {


    var context = Contextor.getInstance().context
    var details = Contextor.getInstance().context.resources.getString(R.string.book_details)
    //var type = Contextor.getInstance().context.resources.getString(R.string.history)

    var bookTitles = context.resources.getStringArray(R.array.dummy_titles_book)
    var bookImages = context.resources.getStringArray(R.array.dummy_images_book)
    var authorNames = context.resources.getStringArray(R.array.dummy_names_author)
    var authorImages = context.resources.getStringArray(R.array.dummy_images_author)
    var categories = context.resources.getStringArray(R.array.dummy_categories)
    var myLists = context.resources.getStringArray(R.array.dummy_my_lists)
    var bannerImages = context.resources.getStringArray(R.array.dummy_images_banner)

    var fakeSize = bookTitles.size - 1

    fun getUsers(): List<User> {

        var items = ArrayList<User>()

        repeat(10) {

            var n = Random.nextInt(fakeSize)

//            var item1 = User(1001, "abc07", "aaa", "", authorNames[n], authorImages[n])
//
//            items.add(item1)

        }

        return items
    }


    fun getBooks(): List<IbdbModel.Book> {

        var items = ArrayList<IbdbModel.Book>()

        repeat(10) {

            var n = Random.nextInt(fakeSize)

            var item1 = IbdbModel.Book(
                1001, bookTitles[n], getAuthors()[n], details,
                4.5, 0.0, categories[n], bookImages[n]
            )

            items.add(item1)

        }

        return items
    }


    fun getAuthors(): List<IbdbModel.Author> {

        var items = ArrayList<IbdbModel.Author>()


        repeat(10) {

            var n = Random.nextInt(fakeSize)

            var item1 = IbdbModel.Author(
                1001, authorNames[n], details,
                4.5, categories[n], authorImages[n]
            )
            items.add(item1)
        }


        return items
    }

    fun getCategories(): List<IbdbModel.Category> {

        var items = ArrayList<IbdbModel.Category>()

        repeat(10) {

            var n = Random.nextInt(fakeSize)
            var item1 = IbdbModel.Category(1001, categories[n])

            items.add(item1)

        }
        return items
    }

    fun getABenificiary(): Beneficiary? {

        val inputStream: InputStream = context.resources.openRawResource(R.raw.single_reg)
        val br = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
        val mapper = ObjectMapper()
        val beneficiary: Beneficiary = mapper.readValue(br, Beneficiary::class.java)
        return beneficiary

//        val txt = context.resources.openRawResource(R.raw.single_reg_mod)
//            .bufferedReader().use { it.readText() }
//
//        val beneficiary: Beneficiary = Gson().fromJson(txt, Beneficiary::class.java)
//
//
//        return null
    }

}