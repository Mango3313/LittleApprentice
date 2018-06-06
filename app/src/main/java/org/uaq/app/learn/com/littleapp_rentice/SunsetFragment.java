package org.uaq.app.learn.com.littleapp_rentice;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

public class SunsetFragment extends Fragment {
    private View mSceneView;
    private View mSunView;
    private View mSkyView;

    private int mBlueSkyColor;
    private int mSunsetSkyColor;
    private int mNightSkyColor;
    private int count = 0;
    public static SunsetFragment newInstance(){
        return  new SunsetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunset,container,false);

        mSceneView = view;
        mSunView = view.findViewById(R.id.sun);
        mSkyView = view.findViewById(R.id.sky);

        Resources resources = getResources();
        mBlueSkyColor = resources.getColor(R.color.blue_sky);
        mSunsetSkyColor = resources.getColor(R.color.sunset_sky);
        mNightSkyColor = resources.getColor(R.color.night_sky);
        mSceneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation(view);
            }
        });

        return view;
    }
    private void startAnimation(View view){
        final View v = view;
        float sunYStart = mSunView.getTop();
        float sunYEnd = mSkyView.getHeight();
        AnimatorSet animatorSet = new AnimatorSet();
            ObjectAnimator heightAnimator = ObjectAnimator.ofFloat(mSunView, "y", sunYStart, sunYEnd)
                    .setDuration(3000);
            heightAnimator.setInterpolator(new AccelerateInterpolator());
            ObjectAnimator sunsetSkyAnimator = ObjectAnimator
                    .ofInt(mSkyView, "backgroundColor", mBlueSkyColor, mSunsetSkyColor)
                    .setDuration(3000);
            sunsetSkyAnimator.setEvaluator(new ArgbEvaluator());
            ObjectAnimator nightSkyAnimator = ObjectAnimator
                    .ofInt(mSkyView, "backgroundColor", mSunsetSkyColor, mNightSkyColor)
                    .setDuration(1500);
            nightSkyAnimator.setEvaluator(new ArgbEvaluator());
            animatorSet.play(heightAnimator)
                    .with(sunsetSkyAnimator)
                    .before(nightSkyAnimator);
            animatorSet.start();
            animatorSet.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    v.setClickable(false);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    v.setClickable(true);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
    }
}
