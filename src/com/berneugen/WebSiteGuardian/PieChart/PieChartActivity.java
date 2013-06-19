package com.berneugen.WebSiteGuardian.PieChart;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.berneugen.WebSiteGuardian.ContentProvider.WebSiteContentProvider;
import com.berneugen.WebSiteGuardian.DBHelper.WebSiteDB;
import com.berneugen.WebSiteGuardian.Model.StatusesModel;
import com.berneugen.WebSiteGuardian.R;
import com.berneugen.WebSiteGuardian.Service.WebSiteService;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created with IntelliJ IDEA.
 * User: Eugen
 * Date: 03.06.13
 * Time: 22:23
 */
public class PieChartActivity extends Activity {

    public static final int GREEN_COLOR = Color.parseColor("#449D5C");
    public static final int RED_COLOR = Color.parseColor("#9D565E");
    private CategorySeries categorySeries;
    private DefaultRenderer renderer;
    private GraphicalView chartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.piechart);

        categorySeries = new CategorySeries("");
        renderer = new DefaultRenderer();

        int okStatusCount = getStatusesCount(StatusesModel.OK_STATUS);
        int failedStatusCount = getStatusesCount(StatusesModel.FAILED_STATUS);

        setVisualStyle();
        drawPie(okStatusCount, failedStatusCount);
    }

    public void drawPie(int okStatusCount, int failedStatusCount) {
        SimpleSeriesRenderer renderOk = new SimpleSeriesRenderer();
        renderOk.setColor(GREEN_COLOR);
        categorySeries.add("OK", okStatusCount);
        renderer.addSeriesRenderer(renderOk);

        SimpleSeriesRenderer renderFail = new SimpleSeriesRenderer();
        renderFail.setColor(RED_COLOR);
        categorySeries.add("Fail", failedStatusCount);
        renderer.addSeriesRenderer(renderFail);
      }

    public void setVisualStyle() {
        renderer.setLabelsColor(Color.BLACK);
        renderer.setLabelsTextSize(22);
        renderer.setShowLegend(false);
    }

    public int getStatusesCount(int status) {
        String[] allStatuses = new String[] {String.valueOf(status)};
        Cursor cursor = getContentResolver().query(WebSiteContentProvider.CONTENT_URI, null, WebSiteDB.STATUS_COLUMN + "=?", allStatuses, null);
        int statusCount = cursor.getCount();
        return statusCount;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (chartView == null) {
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.pie_layout);
            chartView = ChartFactory.getPieChartView(this, categorySeries, renderer);
            linearLayout.addView(chartView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        } else {
            chartView.repaint();
        }
    }
}
























