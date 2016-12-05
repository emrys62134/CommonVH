package com.pei.commonvh;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;




public class VolleySingleton {
    private static VolleySingleton volleySingleton;
    private RequestQueue mRquestQueue;
    private ImageLoader mImageLoader;

    //构造私有化
    private VolleySingleton() {
        mRquestQueue = Volley.newRequestQueue(MyApp.getContext());
        mImageLoader = //初始化ImageLoader
                new ImageLoader(mRquestQueue, new MemoryCache());

    }

    public static VolleySingleton getInstance() {
        if (volleySingleton == null) {

            synchronized (VolleySingleton.class) {
                if (volleySingleton == null) {
                    volleySingleton = new VolleySingleton();
                }
            }
        }
        return volleySingleton;
    }

    //请求图片
    public void getImage(String url, ImageView imageView) {
        mImageLoader.get(url, ImageLoader.getImageListener(
                imageView,
                R.mipmap.ic_launcher,
                R.mipmap.ic_launcher
        ));
        //获取的图片模糊显示
//        mImageLoader.get(url,new An)
    }

    public void getCircleImg(String url, ImageView imageView){
        mImageLoader.get(url,new CircleImgListener(imageView));
    }

    class CircleImgListener implements ImageLoader.ImageListener{
        private ImageView imageView;

        public CircleImgListener(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
            Bitmap bitmap = response.getBitmap();
            if(bitmap == null){
                imageView.setImageResource(R.mipmap.ic_launcher);
            }else {
                CircleDrawable circleDrawable = new CircleDrawable(bitmap);
                imageView.setImageDrawable(circleDrawable);
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            imageView.setImageResource(R.mipmap.ic_launcher);
        }
    }

    //获得requestqueue的方法
    public RequestQueue getRquestQueue() {
        return mRquestQueue;
    }

    public <T> void addRequest(Request<T> request) {
        mRquestQueue.add(request);
    }

    class AnimImageListener implements ImageLoader.ImageListener {
        private ImageView mImageView;
//        这个构造是在模糊显示图片的时候用到的
//        public AnimImageListener(ImageView mImageView) {
//            this.mImageView = mImageView;
//        }

        @Override
        public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
            Bitmap bitmap = response.getBitmap();
            if (bitmap == null) {
                mImageView.setImageResource(R.mipmap.ic_launcher);
            } else {
                mImageView.setImageBitmap(bitmap);
                //添加动画效果
//                AlphaAnimation alphaAnimation = new AlphaAnimation(0,1f);
//                alphaAnimation.setDuration(1000);
//                mImageView.setAnimation(alphaAnimation);
//                alphaAnimation.start();
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            mImageView.setImageResource(R.mipmap.ic_launcher);
        }
    }

}
