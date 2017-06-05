/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.apis.graphics;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;

/**
 * Used by the {@code AnimateDrawables} demo which:
 * Shows how to use the Animation api (in this case TranslateAnimation) in order  to move a jpg
 * around a Canvas. Uses AnimateDrawable which extends ProxyDrawable (A neat way to package the
 * methods required when extending Drawable, overriding only draw in AnimateDrawable)
 */
@SuppressWarnings("WeakerAccess")
public class AnimateDrawable extends ProxyDrawable {

    private Animation mAnimation;
    private Transformation mTransformation = new Transformation();

    @SuppressWarnings("unused")
    public AnimateDrawable(Drawable target) {
        super(target);
    }

    public AnimateDrawable(Drawable target, Animation animation) {
        super(target);
        mAnimation = animation;
    }

    public Animation getAnimation() {
        return mAnimation;
    }

    public void setAnimation(Animation anim) {
        mAnimation = anim;
    }

    @SuppressWarnings("unused")
    public boolean hasStarted() {
        return mAnimation != null && mAnimation.hasStarted();
    }

    @SuppressWarnings("unused")
    public boolean hasEnded() {
        return mAnimation == null || mAnimation.hasEnded();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Drawable dr = getProxy();
        if (dr != null) {
            int sc = canvas.save();
            Animation anim = mAnimation;
            if (anim != null) {
                anim.getTransformation(
                        AnimationUtils.currentAnimationTimeMillis(),
                        mTransformation);
                canvas.concat(mTransformation.getMatrix());
            }
            dr.draw(canvas);
            canvas.restoreToCount(sc);
        }
    }
}

