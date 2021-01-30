package com.skilbox.onboardingandemptyviewpager2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.fragment_article.view.*
import java.lang.Math.abs
import java.util.*
import kotlin.random.Random

class ArticleFragment : Fragment() {

    private var screenArticle: List<ArticleRes> = listOf(
        ArticleRes(
            textRes = R.string.article_1,
            imageRes = R.drawable.article_1,
            articleName = "Цой В.Р.",
            tagRes = createTag()
        ),
        ArticleRes(
            textRes = R.string.article_2,
            imageRes = R.drawable.article_2,
            tagRes = createTag(),
            articleName = "Кинчев К.К."

        ),
        ArticleRes(
            textRes = R.string.article_3,
            imageRes = R.drawable.article_3,
            tagRes = createTag(),
            articleName = "Ревякин Д.А."

        ),
        ArticleRes(
            textRes = R.string.article_4,
            imageRes = R.drawable.article_4,
            tagRes = createTag(),
            articleName = "Летов И.Ф."

        ),
        ArticleRes(
            textRes = R.string.article_5,
            imageRes = R.drawable.article_5,
            tagRes = createTag(),
            articleName = "Бутусов В.Г."

        )

    )

    private val MIN_SCALE = 0.65f
    private val MIN_ALPHA = 0.3f

    private var screenForShow = mutableListOf<ArticleRes>()

    private var adapterScreen: ArticleAdapter? = null
    private lateinit var dotsIndicator: WormDotsIndicator
    private var viewPager: ViewPager2? = null
    val checkedItems = booleanArrayOf(true, true, true, true)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_article, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dotsIndicator = requireView().findViewById(R.id.dots_indicator)
        viewPager = requireView().findViewById<ViewPager2>(R.id.articleViewPager)

        dotsIndicator.setViewPager2(viewPager!!)
    }

    private fun createTag(): List<ArticleTag> {
        val genereteTag = mutableListOf<ArticleTag>()
        val intForRandom = (1..ArticleTag.values().size).random()
        for (i in 0 until intForRandom) {
            genereteTag.add(ArticleTag.values()[Random.nextInt(ArticleTag.values().size)])
        }
        Log.e("CreateTag", "intForRandom=" + intForRandom)
        Log.e("CreateTag", "genereteTag=" + genereteTag)
        return genereteTag.distinct()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager = requireView().findViewById(R.id.articleViewPager)

        retainInstance = true

        toolBarFilter()

        if (screenForShow.isNullOrEmpty()) {
            showArticle(screenArticle)
        } else {
            adapterScreen?.updateScreen(screenForShow)
            showArticle(screenForShow)
        }
    }

    private fun toolBarFilter() {

        my_toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_filter -> {
                    fulterMenu()
                    true
                }
                else -> false
            }
        }
    }

    private fun fulterMenu() {
        val tagListAfterFilter = mutableListOf<ArticleTag>()
        val tagNames = arrayOf(ArticleTag.TECHNOLOGY.tagName, ArticleTag.POLITICS.tagName, ArticleTag.NEWS.tagName, ArticleTag.MUSIC.tagName) // arrayOf("Новости","Политика","Технология","Музыка")

        val builder = context?.let { AlertDialog.Builder(it) }

        builder?.setTitle("Выберите теги:")

        builder?.setMultiChoiceItems(
            tagNames,
            checkedItems
        ) { dialog, which, isChecked ->

            checkedItems[which] = isChecked
        }

        builder?.setPositiveButton("OK") { dialog, which ->

            for (i in checkedItems.indices) {
                val checked = checkedItems[i]
                if (checked) {
                    for (y in 0 until ArticleTag.values().size)
                        if (ArticleTag.values()[y].tagName == Arrays.asList(*tagNames)[i]) {
                            tagListAfterFilter += ArticleTag.values()[y]
                        }
                }
            }

            Log.e("Selected tags", "TagList=" + tagListAfterFilter.distinct()) // Полученный список

            articleFilter(tagListAfterFilter.distinct())
        }

        builder?.setNeutralButton("Cancel") { dialog, which ->
        }
        val dialog = builder?.create()

        dialog?.show()
    }

    private fun articleFilter(tagFilterList: List<ArticleTag>) {

        Log.e("Tags in articleFilter", "TagList=" + tagFilterList) // Полученный список

        val articleForAdapter = mutableListOf<ArticleRes>()

        for (i in 0 until screenArticle.size) {

            for (y in 0 until tagFilterList.size) {

                if (screenArticle[i].tagRes.contains(tagFilterList[y])) {
                    Log.e("articleFilter to if ", screenArticle.get(i).tagRes.toString())
                    articleForAdapter.add(screenArticle[i])
                }
            }
        }

        screenForShow = articleForAdapter.distinct().toMutableList()

        adapterScreen?.updateScreen(screenForShow)

        showArticle(screenForShow)

        Log.e("articleFilter ", "screenForShow" + screenForShow.toString())
    }

    fun showArticle(screen: List<ArticleRes>) {

        adapterScreen = ArticleAdapter(screen, childFragmentManager, lifecycle)
        with(articleViewPager) {
            adapter = adapterScreen
        }

        viewPager?.setPageTransformer { page, position ->
            when {

                position == 1.0F -> {
                    dotsIndicator.visibility = View.INVISIBLE
                }
                position == 0.0f -> {
                    dotsIndicator.visibility = View.INVISIBLE
                }
                else -> {
                    dotsIndicator.visibility = View.VISIBLE
                    page.scaleX = MIN_SCALE.coerceAtLeast(1 - abs(position))
                    page.scaleY = MIN_SCALE.coerceAtLeast(1 - abs(position))
                    page.alpha = MIN_ALPHA.coerceAtLeast(1 - abs(position))
                }
            }
        }

        viewPager?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout2.getTabAt(position)?.removeBadge()
                requireView()
            }
        })

        articleViewPager.offscreenPageLimit = 1
        articleViewPager.currentItem
        articleViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(tabLayout2, articleViewPager) { tab, position ->

            tab.text = if (screenForShow.isNullOrEmpty()) {
                screen[position].articleName
            } else { screenForShow[position].articleName }
        }.attach()

        dots_indicator.setViewPager2(articleViewPager)
    }

    private fun selectedArticle(): List<String> {
        val articleSelected: MutableList<String> = mutableListOf()
        for (i in 0 until adapterScreen?.screenShowNow()?.size!!) {
            for (y in 0 until adapterScreen?.screenShowNow()!![i].tagRes.size) {
                articleSelected.add(adapterScreen?.screenShowNow()!![i].tagRes[y].tagName)
            }
        }
        return articleSelected
    }
}
