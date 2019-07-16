package com.example.humidistat.tmd;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class MyBallView extends View {
    private Paint paint;
    Context context;
    //    private int[] mColors = {Color.RED, Color.MAGENTA};//进度条颜色（渐变色的2个点）
    //圆的初始位置坐标
    private int x = 18;
    private int y = 200;
    private int radius = 25; //圆半径
    private int mWidth;
    private int mHeight;
    private int[] colors = {Color.parseColor("#b20065"), Color.parseColor("#00b5d1"), Color.parseColor("#b20065")};
    private Point startA;
    private Point controlA1;
    private Point controlA2;
    private Point endA;
    private Point startB;
    private Point controlB1;
    private Point controlB2;
    private Point endB;


    public MyBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;


        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();
        mWidth = width;
        mHeight = height;
        x = width / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //用canvas将屏幕设为白色
//        canvas.drawColor(Color.WHITE);

        //设置画笔颜色为红色
        paint = new Paint();
        paint.setColor(Color.parseColor("#E5E5E5"));

        //设置消除锯齿
        paint.setAntiAlias(true);
        //使用画笔绘制圆为小球
        //x :圆心的x坐标
        //y :圆心的y坐标
        //radius ：圆的半径
        //paint ：画笔
        canvas.drawCircle(x - 20, y, radius, paint);

        drawLine(canvas);

        drawCubic(canvas);


        drawDividing(canvas);
    }

    void drawLine(Canvas cv) {
        float lineWodth = 10.0f;
        paint.setStrokeWidth(lineWodth);
        //设置渐变色区域
        LinearGradient shader = new LinearGradient((mWidth / 2) - 25, 0, (mWidth / 2) - 25, mHeight, colors, null,
                Shader.TileMode.CLAMP);
        paint.setShader(shader);

        cv.drawLine((mWidth / 2) - 25, 0, (mWidth / 2) - 25, y - varH, paint);
        cv.drawLine((mWidth / 2) - 25, y + varH, (mWidth / 2) - 25, mHeight, paint);
    }

    int varH = 100;   // 凹陷跨度
    int varV = 50;    // 凹陷突起程度

    void drawCubic(Canvas cvs) {
        paint.setStyle(Paint.Style.STROKE);


        Point center = new Point(x - varH - 25, y);

        startA = new Point(center.x + varH, center.y - varH);
        controlA1 = new Point(center.x + varH, center.y - varH / 2);
        controlA2 = new Point(center.x + varV, center.y - varH / 2);
        endA = new Point(center.x + varV, center.y);
        Path pA = new Path();

        pA.moveTo(startA.x, startA.y);
        pA.cubicTo(controlA1.x, controlA1.y, controlA2.x, controlA2.y, endA.x, endA.y);
        cvs.drawPath(pA, paint);

        startB = new Point(center.x + varV, center.y);
        controlB1 = new Point(center.x + varV, center.y + varH / 2);
        controlB2 = new Point(center.x + varH, center.y + varH / 2);
        endB = new Point(center.x + varH, center.y + varH);
        Path pB = new Path();
        pB.moveTo(startB.x, startB.y);
        pB.cubicTo(controlB1.x, controlB1.y, controlB2.x, controlB2.y, endB.x, endB.y);
        cvs.drawPath(pB, paint);
    }


    void drawDividing(Canvas cv) {
        Point center = new Point(x - varH - 25, y);
        int Dx = x - 40;
        int bigWight = 48;
        int midWight = 18;

        paint.setColor(Color.parseColor("#21243c"));
        paint.setStrokeWidth(2);
        for (int i = 0; i <= mHeight; i += 10) {

            if (i > y - varH && i < y + varH) {
                drawCubicDividing(cv,startA,controlA1,controlA2,endA,midWight,bigWight);
                drawCubicDividing(cv, startB, controlB1, controlB2, endB, midWight, bigWight);
            } else {
                if (i==y+varH){
                    cv.drawLine(Dx, 0 + i, Dx - midWight, 0 + i, paint);
                }
                System.out.println(mHeight + "坐标：" + i);
                if (i % 10 == 0) {
                    cv.drawLine(Dx, 0 + i, Dx - midWight, 0 + i, paint);
                }

                if (i % 20 == 0) {
                    cv.drawLine(Dx, 0 + i, Dx - bigWight, 0 + i, paint);
                }
            }
        }
    }

    void drawCubicDividing(Canvas cv, Point start, Point control1, Point control2, Point end, int midWight, int bigWight) {
        Point[] controlPoint = new Point[4];
        controlPoint[0] = start; //起点
        controlPoint[1] = control1; //控制点
        controlPoint[2] = control2; //控制点
        controlPoint[3] = end; //终点

        int n = controlPoint.length - 1; //
        int i, r;
        float u;

        // u的步长决定了曲线点的精度
        for (u = 0; u <= 1; u += 0.1) {

            Point p[] = new Point[n + 1];
            for (i = 0; i <= n; i++) {
                p[i] = new Point(controlPoint[i].x, controlPoint[i].y);
            }

            for (r = 1; r <= n; r++) {
                for (i = 0; i <= n - r; i++) {
                    p[i].x = (int) ((1 - u) * p[i].x + u * p[i + 1].x);
                    p[i].y = (int) ((1 - u) * p[i].y + u * p[i + 1].y);
                }
            }
            Log.d("521", "drawCubicDividing: " + p[0].y + "\t lkasjkfahfha\t " + (int) (u * 10));
            int varN = (int) (u * 10);
//            if (varN == 0 && varN == 10) {
//
//            } else {
                if (varN % 2 == 0) {
                    cv.drawLine(p[0].x - 20, p[0].y, p[0].x - 20 - midWight, p[0].y, paint);
                } else {
                    cv.drawLine(p[0].x - 20, p[0].y, p[0].x - 10 - bigWight, p[0].y, paint);
                }
//            }

            Log.d("521", "drawCubicDividing: 最U的值："+r*10);
        }

    }

    //实现onTouchEvent方法，处理触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //判断触摸点
        switch (event.getAction()) {
            //实现MotionEvent.ACTION_DOWN，记录按下的x，y坐标：getRawX()和getRawY()获得的是相对屏幕的位置
            case MotionEvent.ACTION_DOWN:
                x = (int) event.getX();
                y = (int) event.getY();
                System.out.println("按下时： " + "x坐标：" + event.getRawX() + "  " + "y坐标：" + event.getRawY());

                //实现MotionEvent.ACTION_MOVE 记录移动的x，y坐标：getRawX()和getRawY()获得的是相对屏幕的位置
            case MotionEvent.ACTION_MOVE:
                x = (int) event.getX();
                y = (int) event.getY();
                System.out.println("移动时： " + "x坐标：" + event.getRawX() + "  " + "y坐标：" + event.getRawY());

                //实现MotionEvent.ACTION_UP 记录抬起的x，y坐标
            case MotionEvent.ACTION_UP:
                // 获取当前触摸点的x,y坐标，为X轴和Y轴坐标重新赋值：getX()和getY()获得的永远是view的触摸位置坐标
                x = (int) event.getX();
                y = (int) event.getY();
                System.out.println("抬起时： " + "x坐标：" + event.getRawX() + "  " + "y坐标：" + event.getRawY());
                break;
        }

        //获取屏幕宽高
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();

        //修正圆点坐标，重新绘制圆 ,控制小球不会被移出屏幕
        if (x >= 18 && y >= 18 && x <= width - 18 && y <= height - 18) {
            /**
             * Android提供了Invalidate方法实现界面刷新，但是Invalidate不能直接在线程中调用，因为他是违背了单线程模型：
             1. Android UI操作并不是线程安全的，并且这些操作必须在UI线程中调用。
             　　 invalidate()是用来刷新View的，必须是在UI线程中进行工作。比如在修改某个view的显示时，调用invalidate()才能看到重新绘制的界面。invalidate()的调用是把之前的旧的view从主UI线程队列中pop掉。
             2.Android 程序默认情况下也只有一个进程，但一个进程下却可以有许多个线程。在这么多线程当中，把主要是负责控
             制UI界面的显示、更新和控件交互的线程称为UI线程，由于onCreate()方法是由UI线程执行的，所以也可以把UI线程理解
             为主线程。其余的线程可以理解为工作者线程。invalidate()得在UI线程中被调动，在工作者线程中可以通过Handler来通
             知UI线程进行界面更新。而postInvalidate()在工作者线程中被调用。
             */
            //使用 postInvalidate()方法实现重绘小球，跟随手指移动
            x = width / 2;

            postInvalidate();
        }
        /*
         * 备注：此处一定要将return super.onTouchEvent(event)修改为return true，原因是：
         * 1）父类的onTouchEvent(event)方法可能没有做任何处理，但是返回了false。
         * 2)一旦返回false，在该方法中再也不会收到MotionEvent.ACTION_MOVE及MotionEvent.ACTION_UP事件。
         */
        //return super.onTouchEvent(event);
        return true;
    }


    /**
     * @param poss      贝塞尔曲线控制点坐标
     * @param precision 精度，需要计算的该条贝塞尔曲线上的点的数目
     * @return 该条贝塞尔曲线上的点（二维坐标）
     */
    public float[][] calculate(float[][] poss, int precision) {

        //维度，坐标轴数（二维坐标，三维坐标...）
        int dimersion = poss[0].length;

        //贝塞尔曲线控制点数（阶数）
        int number = poss.length;

        //控制点数不小于 2 ，至少为二维坐标系
        if (number < 2 || dimersion < 2)
            return null;

        float[][] result = new float[precision][dimersion];

        //计算杨辉三角
        int[] mi = new int[number];
        mi[0] = mi[1] = 1;
        for (int i = 3; i <= number; i++) {

            int[] t = new int[i - 1];
            for (int j = 0; j < t.length; j++) {
                t[j] = mi[j];
            }

            mi[0] = mi[i - 1] = 1;
            for (int j = 0; j < i - 2; j++) {
                mi[j + 1] = t[j] + t[j + 1];
            }
        }

        //计算坐标点
        for (int i = 0; i < precision; i++) {
            float t = (float) i / precision;
            for (int j = 0; j < dimersion; j++) {
                float temp = 0.0f;
                for (int k = 0; k < number; k++) {
                    temp += Math.pow(1 - t, number - k - 1) * poss[k][j] * Math.pow(t, k) * mi[k];
                }
                result[i][j] = temp;
            }
        }

        return result;
    }
}
