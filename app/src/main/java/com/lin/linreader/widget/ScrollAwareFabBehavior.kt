package com.lin.linreader.widget

import android.content.Context
import android.os.Build
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

/**
 * 自定义Fab的Behavior
 */
class ScrollAwareFabBehavior(context: Context, attrs: AttributeSet) : FloatingActionButton.Behavior() {

    private var mIsAnimatingOut = false

   override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout,
                            child: FloatingActionButton,
                            directTargetChild: View,
                            target: View,
                            nestedScrollAxes: Int): Boolean {
        // Ensure we react to vertical scrolling
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes)
    }

    override  fun onNestedScroll(coordinatorLayout: CoordinatorLayout,
                       child: FloatingActionButton,
                       target: View,
                       dxConsumed: Int,
                       dyConsumed: Int,
                       dxUnconsumed: Int,
                       dyUnconsumed: Int) {

        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        if (dyConsumed > 0 && !this.mIsAnimatingOut && child.getVisibility() === View.VISIBLE) {
            // User scrolled down and the FAB is currently visible -> hide the FAB
            animateOut(child)
        } else if (dyConsumed < 0 && child.getVisibility() !== View.VISIBLE) {
            // User scrolled up and the FAB is currently not visible -> show the FAB
            animateIn(child)
        }
    }

    // Same animation that FloatingActionButton.Behavior uses to hide the FAB when the AppBarLayout exits
    private fun animateOut(button: FloatingActionButton) {

        if (Build.VERSION.SDK_INT >= 14) {
            ViewCompat.animate(button).translationY(button.getHeight().toFloat() + getMarginBottom(button)).setInterpolator(INTERPOLATOR).withLayer()
                    .setListener(object : ViewPropertyAnimatorListener {

                        override fun onAnimationStart(view: View) {

                            this@ScrollAwareFabBehavior.mIsAnimatingOut = true
                        }

                        override fun onAnimationCancel(view: View) {

                            this@ScrollAwareFabBehavior.mIsAnimatingOut = false
                        }

                        override fun onAnimationEnd(view: View) {

                            this@ScrollAwareFabBehavior.mIsAnimatingOut = false
                            view.visibility = View.GONE
                        }
                    }).start()
        } else {

        }
    }

    // Same animation that FloatingActionButton.Behavior uses to show the FAB when the AppBarLayout enters
    private fun animateIn(button: FloatingActionButton) {

        button.setVisibility(View.VISIBLE)
        if (Build.VERSION.SDK_INT >= 14) {
            ViewCompat.animate(button).translationY(0f)
                    .setInterpolator(INTERPOLATOR)
                    .withLayer().setListener(null)
                    .start()
        } else {

        }
    }

    private fun getMarginBottom(v: View): Int {

        var marginBottom = 0
        val layoutParams = v.layoutParams
        if (layoutParams is ViewGroup.MarginLayoutParams) {
            marginBottom = layoutParams.bottomMargin
        }
        return marginBottom
    }

    companion object {

        private val INTERPOLATOR = FastOutSlowInInterpolator()
    }
}
