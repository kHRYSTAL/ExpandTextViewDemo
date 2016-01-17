package me.khrystal.expandtextview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.Transformation;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView descriptionView;
	View expandView;
	int maxDescripLine = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		descriptionView = (TextView)findViewById(R.id.description_view);
		expandView = findViewById(R.id.expand_view);
		descriptionView.setText(getText(R.string.content));
		descriptionView.setHeight(descriptionView.getLineHeight() * maxDescripLine);
		descriptionView.post(new Runnable() {
			
			@Override
			public void run() {
				expandView.setVisibility(descriptionView.getLineCount() > maxDescripLine ? View.VISIBLE : View.GONE);
				
			}
		});

		findViewById(R.id.skip).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(MainActivity.this,ListViewActivity.class));
			}
		});

		findViewById(R.id.description_layout).setOnClickListener(new View.OnClickListener() {
			boolean isExpand;

			@Override
			public void onClick(View v) {
				isExpand = !isExpand;
				descriptionView.clearAnimation();
				final int deltaValue;
				final int startValue = descriptionView.getHeight();
				int durationMillis = 350;
				if (isExpand) {
					deltaValue = descriptionView.getLineHeight() * descriptionView.getLineCount() - startValue;
					RotateAnimation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
					animation.setDuration(durationMillis);
					animation.setFillAfter(true);
					expandView.startAnimation(animation);
				} else {
					deltaValue = descriptionView.getLineHeight() * maxDescripLine - startValue;
					RotateAnimation animation = new RotateAnimation(180, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
					animation.setDuration(durationMillis);
					animation.setFillAfter(true);
					expandView.startAnimation(animation);
				}
				Animation animation = new Animation() {
					protected void applyTransformation(float interpolatedTime, Transformation t) {
						descriptionView.setHeight((int) (startValue + deltaValue * interpolatedTime));
					}

				};
				animation.setDuration(durationMillis);
				descriptionView.startAnimation(animation);
			}
		});
	}
}
