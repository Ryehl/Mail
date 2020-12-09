package com.xaoyv.small.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.xaoyv.small.R;
import com.xaoyv.small.bean.Constant;
import com.xaoyv.small.bean.JsonAddGreateBean;
import com.xaoyv.small.bean.JsonCircleBean;
import com.xaoyv.small.utils.NetUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * <p>项目名称:维度商城</p>
 * <p>简述:圈子列表适配器</p>
 *
 * @author Xaoyv
 * date 2020/10/23 10:35
 */
public class CircleAdap extends RecyclerView.Adapter {
    private ArrayList<JsonCircleBean.ResultBean> list;
    private Context context;

    public CircleAdap(ArrayList<JsonCircleBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    class NonePicHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv_hd;
        TextView name;
        TextView date;
        TextView content;
        TextView number;
        ImageView great;

        NonePicHolder(@NonNull View itemView) {
            super(itemView);
            sdv_hd = itemView.findViewById(R.id.item_circle0_sdv_hd);
            name = itemView.findViewById(R.id.item_circle0_tv_name);
            date = itemView.findViewById(R.id.item_circle0_tv_date);
            content = itemView.findViewById(R.id.item_circle0_tv_content);
            number = itemView.findViewById(R.id.item_circle0_tv_number);
            great = itemView.findViewById(R.id.item_circle0_img_great);
        }
    }

    class OnePicHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv_hd;
        TextView name;
        TextView date;
        TextView content;
        SimpleDraweeView sdv_show0;
        TextView number;
        ImageView great;

        OnePicHolder(@NonNull View itemView) {
            super(itemView);
            sdv_hd = itemView.findViewById(R.id.item_circle1_sdv_hd);
            name = itemView.findViewById(R.id.item_circle1_tv_name);
            date = itemView.findViewById(R.id.item_circle1_tv_date);
            content = itemView.findViewById(R.id.item_circle1_tv_content);
            sdv_show0 = itemView.findViewById(R.id.item_circle1_sdv_show0);
            number = itemView.findViewById(R.id.item_circle1_tv_number);
            great = itemView.findViewById(R.id.item_circle1_img_great);
        }
    }

    class TwoPicHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv_hd;
        TextView name;
        TextView date;
        TextView content;
        SimpleDraweeView sdv_show0;
        SimpleDraweeView sdv_show1;
        TextView number;
        ImageView great;

        TwoPicHolder(@NonNull View itemView) {
            super(itemView);
            sdv_hd = itemView.findViewById(R.id.item_circle2_sdv_hd);
            name = itemView.findViewById(R.id.item_circle2_tv_name);
            date = itemView.findViewById(R.id.item_circle2_tv_date);
            content = itemView.findViewById(R.id.item_circle2_tv_content);
            sdv_show0 = itemView.findViewById(R.id.item_circle2_sdv_show0);
            sdv_show1 = itemView.findViewById(R.id.item_circle2_sdv_show1);
            number = itemView.findViewById(R.id.item_circle2_tv_number);
            great = itemView.findViewById(R.id.item_circle2_img_great);
        }
    }

    class ThreePicHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView sdv_hd;
        TextView name;
        TextView date;
        TextView content;
        SimpleDraweeView sdv_show0;
        SimpleDraweeView sdv_show1;
        SimpleDraweeView sdv_show2;
        TextView number;
        ImageView great;

        public ThreePicHolder(@NonNull View itemView) {
            super(itemView);
            sdv_hd = itemView.findViewById(R.id.item_circle3_sdv_hd);
            name = itemView.findViewById(R.id.item_circle3_tv_name);
            date = itemView.findViewById(R.id.item_circle3_tv_date);
            content = itemView.findViewById(R.id.item_circle3_tv_content);
            sdv_show0 = itemView.findViewById(R.id.item_circle3_sdv_show0);
            sdv_show1 = itemView.findViewById(R.id.item_circle3_sdv_show1);
            sdv_show2 = itemView.findViewById(R.id.item_circle3_sdv_show2);
            number = itemView.findViewById(R.id.item_circle3_tv_number);
            great = itemView.findViewById(R.id.item_circle3_img_great);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new NonePicHolder(View.inflate(context, R.layout.recy_item_circle_0, null));
            case 1:
                return new OnePicHolder(View.inflate(context, R.layout.recy_item_circle_1, null));
            case 2:
                return new TwoPicHolder(View.inflate(context, R.layout.recy_item_circle_2, null));
            default:
                return new ThreePicHolder(View.inflate(context, R.layout.recy_item_circle_3, null));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        GenericDraweeHierarchy hierarchy = new GenericDraweeHierarchyBuilder(holder.itemView.getResources())
                .setRoundingParams(RoundingParams.asCircle())
                .build();
        switch (getItemViewType(position)) {
            case 0:
                NonePicHolder nonePicHolder = (NonePicHolder) holder;
                nonePicHolder.sdv_hd.setImageURI(list.get(position).getHeadPic());
                nonePicHolder.sdv_hd.setHierarchy(hierarchy);
                nonePicHolder.name.setText(list.get(position).getNickName());
                nonePicHolder.date.setText(long2Date2String(list.get(position).getCreateTime()));
                nonePicHolder.content.setText(list.get(position).getContent());
                nonePicHolder.number.setText("" + list.get(position).getGreatNum());
                nonePicHolder.great.setOnClickListener(v -> {
                    nonePicHolder.great.setImageResource(R.drawable.great_s);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("", list.get(position).getId());
                    NetUtils.getNetUtils().mIApi.postInfo(Constant.GREAT, map)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ResponseBody>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onNext(ResponseBody responseBody) {
                                    try {
                                        String json = responseBody.string();
                                        JsonAddGreateBean addGreateBean = new Gson().fromJson(json, JsonAddGreateBean.class);
                                        if (!addGreateBean.getStatus().equals("0000")) {
                                            nonePicHolder.great.setImageResource(R.drawable.great_n);
                                            Toast.makeText(context, addGreateBean.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (IOException ignored) {
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(context, "网络错误，点赞失败", Toast.LENGTH_SHORT).show();
                                    nonePicHolder.great.setImageResource(R.drawable.great_n);
                                }

                                @Override
                                public void onComplete() {
                                }
                            });
                });
                break;
            case 1:
                OnePicHolder onePicHolder = (OnePicHolder) holder;
                onePicHolder.sdv_hd.setImageURI(list.get(position).getHeadPic());
                onePicHolder.sdv_hd.setHierarchy(hierarchy);
                onePicHolder.name.setText(list.get(position).getNickName());
                onePicHolder.date.setText(long2Date2String(list.get(position).getCreateTime()));
                onePicHolder.content.setText(list.get(position).getContent());
                onePicHolder.sdv_show0.setImageURI(list.get(position).getImage());
                onePicHolder.number.setText("" + list.get(position).getGreatNum());
                onePicHolder.great.setOnClickListener(v -> {
                    onePicHolder.great.setImageResource(R.drawable.great_s);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("", list.get(position).getId());
                    NetUtils.getNetUtils().mIApi.postInfo(Constant.GREAT, map)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ResponseBody>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onNext(ResponseBody responseBody) {
                                    try {
                                        String json = responseBody.string();
                                        JsonAddGreateBean addGreateBean = new Gson().fromJson(json, JsonAddGreateBean.class);
                                        if (!addGreateBean.getStatus().equals("0000")) {
                                            onePicHolder.great.setImageResource(R.drawable.great_n);
                                            Toast.makeText(context, addGreateBean.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (IOException ignored) {
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(context, "网络错误，点赞失败", Toast.LENGTH_SHORT).show();
                                    onePicHolder.great.setImageResource(R.drawable.great_n);
                                }

                                @Override
                                public void onComplete() {
                                }
                            });
                });
                break;
            case 2:
                TwoPicHolder twoPicHolder = (TwoPicHolder) holder;
                twoPicHolder.sdv_hd.setImageURI(list.get(position).getHeadPic());
                twoPicHolder.sdv_hd.setHierarchy(hierarchy);
                twoPicHolder.name.setText(list.get(position).getNickName());
                twoPicHolder.date.setText(long2Date2String(list.get(position).getCreateTime()));
                twoPicHolder.content.setText(list.get(position).getContent());
                String[] images2 = list.get(position).getImage().split(",");
                twoPicHolder.sdv_show0.setImageURI(images2[0]);
                twoPicHolder.sdv_show1.setImageURI(images2[1]);
                twoPicHolder.number.setText("" + list.get(position).getGreatNum());
                twoPicHolder.great.setOnClickListener(v -> {
                    twoPicHolder.great.setImageResource(R.drawable.great_s);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("", list.get(position).getId());
                    NetUtils.getNetUtils().mIApi.postInfo(Constant.GREAT, map)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ResponseBody>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onNext(ResponseBody responseBody) {
                                    try {
                                        String json = responseBody.string();
                                        JsonAddGreateBean addGreateBean = new Gson().fromJson(json, JsonAddGreateBean.class);
                                        if (!addGreateBean.getStatus().equals("0000")) {
                                            twoPicHolder.great.setImageResource(R.drawable.great_n);
                                            Toast.makeText(context, addGreateBean.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (IOException ignored) {
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(context, "网络错误，点赞失败", Toast.LENGTH_SHORT).show();
                                    twoPicHolder.great.setImageResource(R.drawable.great_n);
                                }

                                @Override
                                public void onComplete() {
                                }
                            });
                });
                break;
            default:
                ThreePicHolder threePicHolder = (ThreePicHolder) holder;
                threePicHolder.sdv_hd.setImageURI(list.get(position).getHeadPic());
                threePicHolder.sdv_hd.setHierarchy(hierarchy);
                threePicHolder.name.setText(list.get(position).getNickName());
                threePicHolder.date.setText(long2Date2String(list.get(position).getCreateTime()));
                threePicHolder.content.setText(list.get(position).getContent());
                String[] images3 = list.get(position).getImage().split(",");
                threePicHolder.sdv_show0.setImageURI(images3[0]);
                threePicHolder.sdv_show1.setImageURI(images3[1]);
                threePicHolder.sdv_show2.setImageURI(images3[2]);
                threePicHolder.number.setText("" + list.get(position).getGreatNum());
                threePicHolder.great.setOnClickListener(v -> {
                    threePicHolder.great.setImageResource(R.drawable.great_s);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("", list.get(position).getId());
                    NetUtils.getNetUtils().mIApi.postInfo(Constant.GREAT, map)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ResponseBody>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                }

                                @Override
                                public void onNext(ResponseBody responseBody) {
                                    try {
                                        String json = responseBody.string();
                                        JsonAddGreateBean addGreateBean = new Gson().fromJson(json, JsonAddGreateBean.class);
                                        if (!addGreateBean.getStatus().equals("0000")) {
                                            threePicHolder.great.setImageResource(R.drawable.great_n);
                                            Toast.makeText(context, addGreateBean.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (IOException ignored) {
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Toast.makeText(context, "网络错误，点赞失败", Toast.LENGTH_SHORT).show();
                                    threePicHolder.great.setImageResource(R.drawable.great_n);
                                }

                                @Override
                                public void onComplete() {
                                }
                            });
                });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        String images = list.get(position).getImage();
        if (images == null || images.length() == 0)
            return 0;
        String[] imgArr = images.split(",");
        return imgArr.length;
    }

    /**
     * format date type of long to string
     *
     * @param date date - type of long
     * @return date - type of string
     */
    private String long2Date2String(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:m:ss");
        return sdf.format(new Date(date));
    }
}
