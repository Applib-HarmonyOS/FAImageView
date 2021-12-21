package kr.pe.burt.hmos.lib.faimageview;

import ohos.agp.components.AttrSet;
import ohos.agp.components.Image;
import ohos.agp.components.element.Element;
import ohos.agp.components.element.PixelMapElement;
import ohos.app.Context;
import ohos.media.image.PixelMap;
import com.hmos.compat.utils.ResourceUtils;
import kr.pe.burt.ohos.lib.ohoschannel.Timer;
import java.util.ArrayList;


/**
 * Created by burt on 15. 10. 22..
 */
public class FaImageView extends Image {

    /**
     * OnStartAnimationListener.
     */
    public interface OnStartAnimationListener {

        void onStartAnimation();
    }

    /**
     * OnFinishAnimationListener.
     */
    public interface OnFinishAnimationListener {

        void onFinishAnimation(boolean isLoopAnimation);
    }

    /**
     * OnFrameChangedListener.
     */
    public interface OnFrameChangedListener {

        void onFrameChanged(int index);
    }

    // 1s
    private static final int DEFAULT_INTERVAL = 1000;

    Timer timer;

    int interval = DEFAULT_INTERVAL;

    ArrayList<Element> drawableList;

    int currentFrameIndex = -1;

    boolean loop = false;

    boolean didStoppedAnimation = true;

    int animationRepeatCount = 1;

    boolean restoreFirstFrameWhenFinishAnimation = true;

    private OnStartAnimationListener startAnimationListener = null;

    private OnFrameChangedListener frameChangedListener = null;

    private OnFinishAnimationListener finishAnimationListener = null;

    public FaImageView(Context context) {
        this(context, null);
    }

    public FaImageView(Context context, AttrSet attrs) {
        this(context, attrs, "0");
    }

    public FaImageView(Context context, AttrSet attrs, String defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * set inteval in milli seconds.
     *
     * @param interval interval of a frame.
     */
    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * addImageFrame method.
     *
     * @param resId resId
     */
    public void addImageFrame(int resId) {
        if (drawableList == null) {
            drawableList = new ArrayList<>();
            setImageElement(ResourceUtils.getDrawable(getContext(), resId));
        }
        drawableList.add(ResourceUtils.getDrawable(getContext(), resId));
    }

    /**
     * addImageFrame method.
     *
     * @param drawable Element
     */
    public void addImageFrame(Element drawable) {
        if (drawableList == null) {
            drawableList = new ArrayList<>();
            setImageElement(drawable);
        }
        drawableList.add(drawable);
    }

    public void addImageFrame(PixelMap bitmap) {
        Element bitmapDrawable = new PixelMapElement(bitmap);
        addImageFrame(bitmapDrawable);
    }

    /**
     * startAnimation method.
     */
    public void startAnimation() {
        if (drawableList == null || drawableList.size() == 0) {
            throw new IllegalStateException("You shoud add frame at least one frame");
        }
        if (!didStoppedAnimation) {
            return;
        }
        didStoppedAnimation = false;
        if (startAnimationListener != null) {
            startAnimationListener.onStartAnimation();
        }
        // for implementing resume
        if (currentFrameIndex == -1) {
            currentFrameIndex = 0;
        }
        setImageElement(drawableList.get(currentFrameIndex));
        if (timer == null) {
            timer = new Timer(interval, new Timer.OnTimer() {

                @Override
                public void onTime(Timer timer) {
                    if (didStoppedAnimation) {
                        return;
                    }
                    currentFrameIndex++;
                    startAnimationCurrentFrameIndex();
                    startAnimationDidStoppedAnimation();
                }
            });
            timer.stop();
        }
        if (!timer.isAlive()) {
            timer.start();
        }
    }

    private void startAnimationDidStoppedAnimation() {
        if (!didStoppedAnimation) {
            if (frameChangedListener != null) {
                frameChangedListener.onFrameChanged(currentFrameIndex);
            }
            setImageElement(drawableList.get(currentFrameIndex));
        } else {
            if (restoreFirstFrameWhenFinishAnimation) {
                setImageElement(drawableList.get(0));
            }
            currentFrameIndex = -1;
        }
    }

    private void startAnimationCurrentFrameIndex() {
        if (currentFrameIndex == drawableList.size()) {
            if (loop) {
                if (finishAnimationListener != null) {
                    finishAnimationListener.onFinishAnimation(loop);
                }
                currentFrameIndex = 0;
            } else {
                animationRepeatCount--;
                if (animationRepeatCount <= 0) {
                    currentFrameIndex = drawableList.size() - 1;
                    stopAnimation();
                    if (finishAnimationListener != null) {
                        finishAnimationListener.onFinishAnimation(loop);
                    }
                } else {
                    currentFrameIndex = 0;
                }
            }
        }

    }

    public boolean isAnimating() {
        return !didStoppedAnimation;
    }

    /**
     * stopAnimation method.
     */
    public void stopAnimation() {
        if (timer != null && timer.isAlive()) {
            timer.stop();
        }
        timer = null;
        didStoppedAnimation = true;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public void setRestoreFirstFrameWhenFinishAnimation(boolean restore) {
        this.restoreFirstFrameWhenFinishAnimation = restore;
    }

    public void setAnimationRepeatCount(int animationRepeatCount) {
        this.animationRepeatCount = animationRepeatCount;
    }

    /**
     * reset method.
     */
    public void reset() {
        stopAnimation();
        if (drawableList != null) {
            drawableList.clear();
            drawableList = null;
        }
        currentFrameIndex = -1;
    }

    public void setOnStartAnimationListener(OnStartAnimationListener listener) {
        startAnimationListener = listener;
    }

    public void setOnFrameChangedListener(OnFrameChangedListener listener) {
        frameChangedListener = listener;
    }

    public void setOnFinishAnimationListener(OnFinishAnimationListener listener) {
        finishAnimationListener = listener;
    }
}
