package gam.trainingcourse.annht2_advanceandroid_day6_component2.adapter.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.bumptech.glide.Glide
import gam.trainingcourse.annht2_advanceandroid_day6_component2.R

class DataBindingAdapter {

    companion object{

        @JvmStatic
        @BindingAdapter("setLoadImage")
        fun setLoadImage(view: ImageView, url: String?) {
            if (!url.isNullOrEmpty())
                Glide.with(view.context).load(url).placeholder(R.drawable.beginner3).dontAnimate().into(view);
        }


        @JvmStatic
        @BindingAdapter("setTextData")
        fun setTextData(view:TextView, name:String?){
            view.text = name
        }
    }


    @BindingMethods(
        value =[BindingMethod(
            type = TextView::class,
            attribute ="android:text",
            method = "setText"
        ),
        BindingMethod(
            type = ImageView::class,
            attribute = "setLoadImage",
            method = "setUrl"
        )]

    )
    class ImageViewBindingAdapter

}