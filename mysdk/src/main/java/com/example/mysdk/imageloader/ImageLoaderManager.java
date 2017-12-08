package com.example.mysdk.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.widget.ImageView;

import com.example.mysdk.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * @author gentleman
 * @function 初始化UniversalImageLoader,并用来加载图片
 * Created by gentleman on 2017/12/8.
 */

public class ImageLoaderManager  {

    /**
     * 默认参数值
     */
    private static final int THREAD_COUNT = 4; //标明我们的UIL最多可以有多少条线程
    private static final int PRIORITY = 2; //标明图片加载的一个优先级
    private static final int DISK_CACHE_SIZE = 50*1024; //标明UIL最多可以缓存多少图片
    private static final int CONNECTION_TIME_OUT = 5*1000; //连接的超时时间
    private static final int READ_TIME_OUT = 30*1000;//读取的超时时间

    public static ImageLoaderManager mInstance=null;
    private static ImageLoader mImageLoader=null;


    //私有构造方法
    private ImageLoaderManager(Context context){
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(THREAD_COUNT)//配置图片下载的最大线程数
                .threadPriority(Thread.NORM_PRIORITY-PRIORITY)//设置图片下载的优先级
                .denyCacheImageMultipleSizesInMemory()//防止缓存多套尺寸的图片到内存中
                .memoryCache(new WeakMemoryCache())//使用弱引用内存缓存
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())//使用MD5命名文件
                .diskCacheSize(DISK_CACHE_SIZE)//分配硬盘缓存大小
                .tasksProcessingOrder(QueueProcessingType.LIFO)//图片下载顺序
                .defaultDisplayImageOptions(getDefaultOptions())//默认的图片加载Options
                .imageDownloader(new BaseImageDownloader(context,CONNECTION_TIME_OUT,READ_TIME_OUT))//设置图片下载器
                .writeDebugLogs()//在debug的时候打印日志
                .build();

        //初始化ImageLoader
        ImageLoader.getInstance().init(configuration);
        mImageLoader = ImageLoader.getInstance();



    }

    //单例模式，获取Instance
    public static ImageLoaderManager getInstance(Context context){

        if (mInstance==null){
            synchronized (ImageLoaderManager.class){
                if (mInstance==null){
                    mInstance = new ImageLoaderManager(context);
                }
            }
        }

        return mInstance;
    }


    /**
     * 图片显示的参数设置
     * @return options
     */
    public DisplayImageOptions getDefaultOptions() {
        //构建者模式
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.xadsdk_img_error)//url为null的时候显示的图片
                .showImageOnFail(R.drawable.xadsdk_img_error)//图片加载失败的时候显示的图片
                .cacheOnDisk(true)//设置图片可以缓存到硬盘
                .cacheInMemory(true)//设置图片可以缓存到内存上
                .bitmapConfig(Bitmap.Config.RGB_565)//使用图片的编码类型
                .decodingOptions(new BitmapFactory.Options())//图片解码配置
                .build();
        return options;
    }


    //显示图片
    public void displayImage(String url, ImageView imageView, DisplayImageOptions options, ImageLoadingListener listener){
        if (mImageLoader!=null){
            mImageLoader.displayImage(url, imageView,options,listener);
        }
    }



    public void displayImage(String url, ImageView imageView, ImageLoadingListener listener){
        displayImage(url,imageView,null,listener);

    }


    public void displayImage(String url,ImageView imageView){
        displayImage(url,imageView,null);
    }


}
