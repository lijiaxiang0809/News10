package razerdp.design;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.TextView;
import razerdp.basepopup.BasePopupWindow;
import razerdp.library.R;

public class DialogPopup extends BasePopupWindow implements View.OnClickListener{

    private final TextView mContent;

    public interface DialogClickCallBack{
        void okClick();
        void cancelClick();
    }
    private DialogClickCallBack mDialogClickCallBack;

    public void setDialogClickCallBack(DialogClickCallBack callBack){
        this.mDialogClickCallBack = callBack;
    }

    private TextView ok;
    private TextView cancel;

    public DialogPopup(Activity context,boolean canCancel) {
        super(context);

        ok= (TextView) findViewById(R.id.ok);
        cancel= (TextView) findViewById(R.id.cancel);
        mContent = (TextView) findViewById(R.id.content);
        cancel.setVisibility(canCancel ? View.VISIBLE : View.GONE);
        setViewClickListener(this,ok,cancel);
    }

    public void setContent(String content){
        mContent.setText(content);
    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set=new AnimationSet(false);
        Animation shakeAnima=new RotateAnimation(0,15, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);
        return set;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_dialog);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ok) {
            if (mDialogClickCallBack != null) mDialogClickCallBack.okClick();
        } else if (i == R.id.cancel) {
            if (mDialogClickCallBack != null) mDialogClickCallBack.cancelClick();
        }
    }
}
