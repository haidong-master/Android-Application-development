package my.book.musicplayer.tool;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import my.book.musicplayer.R;

public class FlingGalleryView extends ViewGroup {

	private static final int SNAP_VELOCITY = 1000;
	// ��¼��ǰ��Ļ�±꣬ȡֵ��Χ�ǣ�0 �� getChildCount()-1
	private int mCurrentScreen;
	private Scroller mScroller;
	// �ٶ�׷��������Ҫ��Ϊ��ͨ��ǰ�����ٶ��жϵ�ǰ�����Ƿ�Ϊfling
	private VelocityTracker mVelocityTracker;
	// ��¼����ʱ�ϴ���ָ���λ��
	private float mLastMotionX;
	private float mLastMotionY;
	// Touch״ֵ̬ 0����ֹ 1������
	private final static int TOUCH_STATE_REST = 0;
	private final static int TOUCH_STATE_SCROLLING = 1;
	// ��¼��ǰtouch�¼�״̬--������TOUCH_STATE_SCROLLING������ֹ��TOUCH_STATE_REST Ĭ�ϣ�
	private int mTouchState = TOUCH_STATE_REST;
	// ��¼touch�¼��б���Ϊ�ǻ����¼�ǰ�����ɻ�������
	private int mTouchSlop;
	// ��ָ�׶���������ٶ�px/s ÿ���������
	private int mMaximumVelocity;
	// ������ָ����Ļ���¼�
	private OnScrollToScreenListener mScrollToScreenListener;
	// �Զ���touch�¼�
	private OnCustomTouchListener mCustomTouchListener;
	//������ÿ����Ļʱ�Ƿ�Ҫ����OnScrollToScreenListener�¼�
	private boolean isEveryScreen=false;

	public FlingGalleryView(Context context) {
		super(context);
		init();
		mCurrentScreen = 0;
	}

	public FlingGalleryView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FlingGalleryView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.FlingGalleryView, defStyle, 0);
		mCurrentScreen = a.getInt(R.styleable.FlingGalleryView_defaultScreen, 0);
		a.recycle();

		init();
	}

	private void init() {
		mScroller = new Scroller(getContext());
		final ViewConfiguration configuration = ViewConfiguration
				.get(getContext());
		mTouchSlop = configuration.getScaledTouchSlop();
		mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
	}

	// ��֤��ͬһ����Ļִ��һ�������¼���һЩ����
	private int count = -1;
	private int defaultScreen = -1;

	// ������������ʱ���ã�startScroll()���õ��ǲ���ʵ�ʻ�����������ִ�У�
	@Override
	public void computeScroll() {
		// mScroller.computeScrollOffset���㵱ǰ�µ�λ�ã�true��ʾ���ڻ������������
		if (mScroller.computeScrollOffset()) {
			// ����true��˵��scroll��û��ֹͣ
			scrollTo(mScroller.getCurrX(), 0);
			if(isEveryScreen)singleScrollToScreen();
			postInvalidate();
		}
	}

	// ��֤��ͬһ����Ļִ��һ�������¼�
	private void singleScrollToScreen() {
		final int screenWidth = getWidth();
		int whichScreen = (getScrollX() + (screenWidth / 2)) / screenWidth;
		if (whichScreen > (getChildCount() - 1)) {
			return;
		}
		if (defaultScreen == -1) {
			defaultScreen = whichScreen;
			count = 1;
		} else {
			if (defaultScreen == whichScreen && count == 0) {
				count = 1;
			} else {
				if (defaultScreen != whichScreen) {
					defaultScreen = whichScreen;
					count = 0;
				}
			}
		}
		if (count == 0) {
			if (mScrollToScreenListener != null) {
				mScrollToScreenListener.operation(whichScreen, getChildCount());
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int width = MeasureSpec.getSize(widthMeasureSpec);
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		if (widthMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException(
					"Workspace can only be used in EXACTLY mode.");
		}
		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		if (heightMode != MeasureSpec.EXACTLY) {
			throw new IllegalStateException(
					"Workspace can only be used in EXACTLY mode.");
		}
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
		}
		scrollTo(mCurrentScreen * width, 0);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		int childLeft = 0;
		// ����ƽ��childView
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);
			child.setOnTouchListener(childTouchListener);
			if (child.getVisibility() != View.GONE) {
				final int childWidth = child.getMeasuredWidth();
				child.layout(childLeft, 0, childLeft + childWidth,
						child.getMeasuredHeight());
				childLeft += childWidth;
			}
		}
	}

	// �趨childView��Touch�¼�����true�����������parentView�нػ�touch����onInterceptTouchEvent����move,up���¼�
	private OnTouchListener childTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			return true;
		}
	};

	// ��ϵͳ���ViewGroup�������childView����onTouchEvent()֮ǰ������¼�����һ������
	/*
	 * down�¼����Ȼᴫ�ݵ�onInterceptTouchEvent()����
	 * ����ViewGroup��onInterceptTouchEvent()�ڽ��յ�down�¼��������֮��return
	 * false����ô�����move,
	 * up���¼���������ȴ��ݸ��ViewGroup��֮��ź�down�¼�һ��ݸ����յ�Ŀ��view��onTouchEvent()���?
	 * ����ViewGroup��onInterceptTouchEvent()�ڽ��յ�down�¼��������֮��return
	 * true����ô�����move,
	 * up���¼������ٴ��ݸ�onInterceptTouchEvent()�����Ǻ�down�¼�һ��ݸ��ViewGroup��onTouchEvent
	 * ()���?ע�⣬Ŀ��view�����ղ����κ��¼���
	 * ���������Ҫ�����¼���view��onTouchEvent()������false����ô���¼���������������һ��ε�view��onTouchEvent
	 * ()���? ���������Ҫ�����¼���view
	 * ��onTouchEvent()������true����ô�����¼������Լ���ݸ��view��onTouchEvent()���?
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (mCustomTouchListener != null) {
			mCustomTouchListener.operation(ev);
		}
		final int action = ev.getAction();
		if ((action == MotionEvent.ACTION_MOVE)
				&& (mTouchState != TOUCH_STATE_REST)) {
			return true;
		}
		final float x = ev.getX();
		final float y = ev.getY();
		switch (action) {
		case MotionEvent.ACTION_MOVE:
			// ����X�����ƶ��ľ���
			final int xDiff = (int) Math.abs(x - mLastMotionX);
			final int touchSlop = mTouchSlop;
			if (xDiff > touchSlop) {
				// �ƶ�����С��45��ʱ��X��������ƶ�
				if (Math.abs(mLastMotionY - y) / Math.abs(mLastMotionX - x) < 1) {
					mTouchState = TOUCH_STATE_SCROLLING;
				}
			}
			break;
		case MotionEvent.ACTION_DOWN:
			mLastMotionX = x;
			mLastMotionY = y;
			mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST
					: TOUCH_STATE_SCROLLING;
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mTouchState = TOUCH_STATE_REST;
			break;
		}
		return mTouchState != TOUCH_STATE_REST;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(ev);
		final int action = ev.getAction();
		final float x = ev.getX();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (!mScroller.isFinished()) {
				// ��ֹ�������Ļ�������
				mScroller.abortAnimation();
			}
			mLastMotionX = x;
			count = -1;
			defaultScreen = -1;
			break;
		case MotionEvent.ACTION_MOVE:
			if (mTouchState == TOUCH_STATE_SCROLLING) {
				final float t_width = (getWidth() / 4f);
				// ���һ����Ļ�����ƶ�ʱ�����ܳ�����Ļ��4��֮һ
				if (getScrollX() > ((getChildCount() - 1) * getWidth() + t_width)) {
					break;
				}
				// ��һ����Ļ�����ƶ�ʱ�����ܳ�����Ļ��4��֮һ
				if (getScrollX() < ((t_width) * -1)) {
					break;
				}
				final int deltaX = (int) (mLastMotionX - x);
				mLastMotionX = x;
				scrollBy(deltaX, 0);
			}
			break;
		case MotionEvent.ACTION_UP:
			if (mTouchState == TOUCH_STATE_SCROLLING) {
				final VelocityTracker velocityTracker = mVelocityTracker;
				velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);// ʹ��pix/sΪ��λ
				int velocityX = (int) velocityTracker.getXVelocity();
				if (velocityX > SNAP_VELOCITY && mCurrentScreen > 0) {
					// �����ƶ�
					snapToScreen(mCurrentScreen - 1, false);
				} else if (velocityX < -SNAP_VELOCITY
						&& mCurrentScreen < getChildCount() - 1) {
					// �����ƶ�
					snapToScreen(mCurrentScreen + 1, false);
				} else {
					snapToDestination();
				}
				if (mVelocityTracker != null) {
					mVelocityTracker.recycle();
					mVelocityTracker = null;
				}
			}
			mTouchState = TOUCH_STATE_REST;
			break;
		case MotionEvent.ACTION_CANCEL:
			mTouchState = TOUCH_STATE_REST;
		}
		return true;
	}

	// ����Ӧ��ȥ�ĸ���
	private void snapToDestination() {
		final int screenWidth = getWidth();
		// ������Ļ��һ���������һ����
		final int whichScreen = (getScrollX() + (screenWidth / 2))/ screenWidth;
		snapToScreen(whichScreen, false);
	}

	// �л���Ļ
	private void snapToScreen(int whichScreen, boolean isJump) {
		// �ж���һ����Ļ�Ƿ���Ч��������
		whichScreen = Math.max(0, Math.min(whichScreen, getChildCount() - 1));
		if (getScrollX() != (whichScreen * getWidth())) {
			final int delta = whichScreen * getWidth() - getScrollX();
			count = -1;
			defaultScreen = -1;
			// ��ʼ��������
			mScroller.startScroll(getScrollX(), 0, delta, 0,
					Math.abs(delta) * 2);
			final int t_mCurrentScreen = mCurrentScreen;
			mCurrentScreen = whichScreen;
			// �ж��Ƿ���ͬһ����Ļ��������ִ���л���Ļ
			if (t_mCurrentScreen != whichScreen) {
				// ��ֹ�ظ�ִ���л���Ļ�¼�
				if (Math.abs(t_mCurrentScreen - whichScreen) == 1 && !isJump) {
					doOnScrollToScreen();
				}
			}
			invalidate();
		}
	}

	private void doOnScrollToScreen() {
		if (mScrollToScreenListener != null) {
			mScrollToScreenListener.operation(mCurrentScreen, getChildCount());
		}
	}

	/**
	 * �����л�����ָ���±���Ļ0��getChildCount()-1
	 * */
	public void setToScreen(int whichScreen, boolean isAnimation) {
		if (isAnimation) {
			snapToScreen(whichScreen, true);
		} else {
			whichScreen = Math.max(0,
					Math.min(whichScreen, getChildCount() - 1));
			mCurrentScreen = whichScreen;
			// ֱ�ӹ�������λ��
			scrollTo(whichScreen * getWidth(), 0);
			if (whichScreen != mCurrentScreen) {
				doOnScrollToScreen();
			}
			invalidate();
		}
	}

	/**
	 * ����Ĭ����Ļ���±�
	 * */
	public void setDefaultScreen(int defaultScreen) {
		mCurrentScreen = defaultScreen;
	}

	/**
	 * ��ȡ��ǰ��Ļ���±�
	 * */
	public int getCurrentScreen() {
		return mCurrentScreen;
	}

	/**
	 * ע�������ָ����Ļ���¼�
	 * */
	public void setOnScrollToScreenListener(
			OnScrollToScreenListener scrollToScreenListener) {
		if (scrollToScreenListener != null) {
			this.mScrollToScreenListener = scrollToScreenListener;
		}
	}

	/**
	 * ע���Զ���Touch�¼�
	 * */
	public void setOnCustomTouchListener(
			OnCustomTouchListener customTouchListener) {
		if (customTouchListener != null) {
			this.mCustomTouchListener = customTouchListener;
		}
	}

	/**
	 * ������ָ����Ļ���¼����������¼���
	 * */
	public interface OnScrollToScreenListener {
		public void operation(int currentScreen, int screenCount);
	}

	/**
	 * �Զ����һ��Touch�¼�
	 * */
	public interface OnCustomTouchListener {
		public void operation(MotionEvent event);
	}
	
	/**
	 * ������ÿ����Ļʱ�Ƿ�Ҫ����OnScrollToScreenListener�¼�
	 * */
	public void setEveryScreen(boolean isEveryScreen) {
		this.isEveryScreen = isEveryScreen;
	}
}
