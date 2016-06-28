package me.card.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.young.lee.activity.MApplication;
import com.young.lee.activity.MainActivity;
import com.young.lee.activity.R;

/**
 * 卡片View项
 * 
 * @author xmuSistone
 */
@SuppressLint("NewApi")
public class CardItemView extends LinearLayout {

	public ImageView imageView;
	private TextView userNameTv;
	private TextView imageNumTv;
	private TextView likeNumTv;
	private ImageLoader mimageLoader;

	public CardItemView(Context context) {
		this(context, null);
	}

	public CardItemView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CardItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		inflate(context, R.layout.card_item, this);
		imageView = (ImageView) findViewById(R.id.card_image_view);
		userNameTv = (TextView) findViewById(R.id.card_user_name);
		imageNumTv = (TextView) findViewById(R.id.card_pic_num);
		likeNumTv = (TextView) findViewById(R.id.card_like);
		mimageLoader = MApplication.getImageLoader();
	}

	public void fillData(CardDataItem itemData) {
		// ImageLoader.getInstance().displayImage(itemData.imagePath,
		// imageView);
		mimageLoader.get(itemData.getImages().get(0), ImageLoader
				.getImageListener(imageView, R.drawable.ic_launcher,
						R.drawable.ic_launcher));
		userNameTv.setText(itemData.getTitle());
		imageNumTv.setText(itemData.getId() + "");
		likeNumTv.setText(itemData.getType() + "");
	}
}
