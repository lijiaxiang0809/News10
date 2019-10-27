package razerdp.design;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import java.util.List;

import razerdp.basepopup.BasePopupWindow;
import razerdp.library.R;

public class WheelViewPopup extends BasePopupWindow {

    private View popupView;
    private WheelView mWheelView;
    public TextView mConfirm;

    public WheelViewPopup(Activity context) {
        super(context);
        bindEvent();
    }



    public void setWheelData(List<String> pData) {
        if (mWheelView != null) mWheelView.setData(pData);
    }

    public void setDefaultSelected(int index){
        mWheelView.setDefault(index);
    }

    public int getSelectedPos(){
        return mWheelView.getSelected();
    }

    public String getSelectedText(){
        return mWheelView.getSelectedText();
    }

    @Override
    protected Animation initShowAnimation() {
        return getTranslateVerticalAnimation(1f, 0, 500);
    }

    @Override
    protected Animation initExitAnimation() {
        return getTranslateVerticalAnimation(0, 1f, 500);
    }

    @Override
    public View getClickToDismissView() {
        return popupView.findViewById(R.id.cancel);
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.wheel_slide_from_bottom, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView != null) {
            mWheelView = popupView.findViewById(R.id.wheel_view);
            mConfirm = popupView.findViewById(R.id.confirm);
        }
    }
}
