package org.com.art.application.sample

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import butterknife.ButterKnife
import butterknife.InjectView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SimpleTarget
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import org.wangjie.wheelview.R

class GalleryActivity : AppCompatActivity() {
    var orderNumber = 0
    private lateinit var _images: Array<String>
    private var _adapter: GalleryPagerAdapter? = null
    @JvmField
    @InjectView(R.id.pager)
    var _pager: ViewPager? = null
    @JvmField
    @InjectView(R.id.thumbnails)
    var thumbnails: LinearLayout? = null
    @JvmField
    @InjectView(R.id.btn_close)
    var closeButton: ImageButton? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery2)
        ButterKnife.inject(this)
        _images = intent.getSerializableExtra(EXTRA_NAME) as Array<String>
        orderNumber = intent.getSerializableExtra(EXTRA_NUMBER) as Int
        _adapter = GalleryPagerAdapter(this)
        _pager!!.adapter = _adapter
        _pager!!.currentItem = orderNumber // Ссетит пейджер на определенную позицию
        _pager!!.offscreenPageLimit = _images.size // how many images to load into memory
        // _pager.setCurrentItem(orderNumber);  // Ссетит пейджер на определенную позицию меняет позицию нижних картинок
        closeButton!!.setOnClickListener {
            Log.d(TAG, "Close clicked")
            finish()
        }
    }

    internal inner class GalleryPagerAdapter(var _context: Context) : PagerAdapter() {
        var _inflater: LayoutInflater
        override fun getCount(): Int {
            return _images.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object` as LinearLayout
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val itemView = _inflater.inflate(R.layout.pager_gallery_item2, container, false)
            container.addView(itemView)
            // Get the border size to show around each image
            val borderSize = thumbnails!!.paddingTop
            // Get the size of the actual thumbnail image
            val thumbnailSize = (_pager!!.layoutParams as FrameLayout.LayoutParams).bottomMargin - borderSize * 2
            // Set the thumbnail layout parameters. Adjust as required
            val params = LinearLayout.LayoutParams(thumbnailSize, thumbnailSize)
            params.setMargins(0, 0, borderSize, 0)
            // You could also set like so to remove borders
//ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
//        ViewGroup.LayoutParams.WRAP_CONTENT,
//        ViewGroup.LayoutParams.WRAP_CONTENT);
//            final ImageView thumbView = new ImageView(_context);  // Нижняя полоса с картинами
//            thumbView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            thumbView.setLayoutParams(params);
//            thumbView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d(TAG, "Thumbnail clicked");
//
//
//                    // Set the pager position when thumbnail clicked
//                    _pager.setCurrentItem(position);  // Ссетит пейджер на определенную позицию
//                }
//            });
//
//
//            _thumbnails.addView(thumbView);
            val imageView = itemView.findViewById<View>(R.id.image) as SubsamplingScaleImageView //  Тут основная картинка
            // Asynchronously load the image and set the thumbnail and pager view
//            Glide.with(_context)
//                    .load(_images[position])
//                    .asBitmap()
//                    .into(new SimpleTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
//                            imageView.setImage(ImageSource.bitmap(bitmap));
//                          //  thumbView.setImageBitmap(bitmap);
//                        }
//                    });
            orderNumber = intent.getSerializableExtra(EXTRA_NUMBER) as Int
            Glide.with(_context)
                    .load(_images[position])
                    .asBitmap()
                    .into(object : SimpleTarget<Bitmap?>() {
                        override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap?>?) {
                            imageView.setImage(ImageSource.bitmap(resource))
                        }
                    })
            return itemView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as LinearLayout)
        }

        init {
            _inflater = _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
    }

    companion object {
        const val TAG = "GalleryActivity"
        const val EXTRA_NAME = "images"
        const val EXTRA_NUMBER = "number"
    }
}
