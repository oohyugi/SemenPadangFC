package com.spfc.oohyugi.semenpadangfc.core.liga

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.spfc.oohyugi.semenpadangfc.R
import com.spfc.oohyugi.semenpadangfc.base.BaseFragment
import com.spfc.oohyugi.semenpadangfc.commons.inflate
import com.spfc.oohyugi.semenpadangfc.di.component.ActivityComponent
import kotlinx.android.synthetic.main.webviewfragment.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.jsoup.Jsoup
import android.webkit.WebSettings



/**
 * Created by oohyugi on 9/16/17.
 */
class WebViewFragment: BaseFragment() {
    override fun injectModule(activityComponent: ActivityComponent) {

    }

    val TAG_ID="id"
    fun newsInstance(i: Int): WebViewFragment {
        val bundle = Bundle()
        val fragment = WebViewFragment()
        bundle.putInt(TAG_ID,i)
        fragment.setArguments(bundle)
        return fragment
    }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.webviewfragment)

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments.get(TAG_ID)
       if (id==0){
           progressbar.visibility
            doAsync {

                val url = "http://semenpadangfc.co.id/fixtures-results/"
                val htmlDocument = Jsoup.connect(url).get()

                val rows = htmlDocument.select("div[class=fixture-result-item-holder]").html()
                val css = htmlDocument.select("head").html()
                uiThread {
                    val settings = webview.getSettings()
                    settings.setDefaultTextEncodingName("utf-8")
                    settings.javaScriptEnabled


//                    webview.loadDataWithBaseURL(null, rows, "mime", "encoding", null)
                    progressbar.visibility=View.GONE
                    webview.settings.javaScriptEnabled
                    webview.loadDataWithBaseURL(null,"<html>$css<body>$rows</body></html>", "text/html; charset=utf-8",null,null)
                }
            }


        }else{
            doAsync {


                val url = "http://semenpadangfc.co.id"
                val htmlDocument = Jsoup.connect(url).get()

                val rows = htmlDocument.select("div[class=gdlr-item gdlr-league-table-item]").html()
                val css = htmlDocument.select("head").html()
                println(css)
                uiThread {
                    val settings = webview.getSettings()
                    settings.setDefaultTextEncodingName("utf-8")
                    settings.javaScriptEnabled
                    progressbar.visibility=View.GONE
//                    webview.loadDataWithBaseURL("<html>$css<body$rows></body></html>" , "text/html; charset=utf-8")
                    webview.settings.javaScriptEnabled
                    webview.loadDataWithBaseURL(null,"<html>$css<body>$rows</body></html>", "text/html; charset=utf-8",null,null)
                }
            }
        }
    }

}