package razerdp.design;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import razerdp.basepopup.BasePopupWindow;
import razerdp.library.R;

public class SlideFromBottomPopup extends BasePopupWindow {

    private View popupView;
    private TextView mText1;
    private TextView mText2;
    private TextView mText3;

    public SlideFromBottomPopup(Activity context) {
        super(context);
        bindEvent();
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
        return popupView.findViewById(R.id.click_to_dismiss);
    }

    @Override
    public View onCreatePopupView() {
        popupView = LayoutInflater.from(getContext()).inflate(R.layout.popup_slide_from_bottom, null);
        return popupView;
    }

    @Override
    public View initAnimaView() {
        return popupView.findViewById(R.id.popup_anima);
    }

    private void bindEvent() {
        if (popupView != null) {
            mText1 = popupView.findViewById(R.id.tx_1);
            mText2 = popupView.findViewById(R.id.tx_2);
            mText3 = popupView.findViewById(R.id.tx_3);
            mText1.setOnClickListener(
                    new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sBottomPopClick != null)
                    sBottomPopClick.clickTop();
                }
            });
            mText2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sBottomPopClick != null)
                    sBottomPopClick.clickCenter();
                }
            });
            mText3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sBottomPopClick != null)
                    sBottomPopClick.clickBottom();
                }
            });
        }

    }

    public void setLineText(String top,String center,String bottom){
        mText1.setText(top);
        mText2.setText(center);
        mText3.setText(bottom);
    }

    public interface BottomPopClick{
        void clickTop();
        void clickCenter();
        void clickBottom();
    }

    public BottomPopClick sBottomPopClick;
    public void setBottomClickListener(BottomPopClick clickListener){
        sBottomPopClick = clickListener;
    }

}
