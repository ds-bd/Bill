package com.mm.bill;

import com.mm.bill.floatingactionbutton.FloatingActionButton;
import com.mm.bill.floatingactionbutton.FloatingActionMenu;
import com.mm.bill.floatingactionbutton.SubActionButton;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BillFragment extends BaseFragment implements OnClickListener {

    private static final String TAG = "BillFragment";

    // private Button mAddBill;
    // private FloatingActionButton mAddBill;
    
    private ImageView mAddBill;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        if (mViewGroup == null) {
            mContext = getActivity().getBaseContext();
            mViewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_bill_detail, null);
            setupView();
            initAddButton();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
        }

    }

    private void showCustomDialog() {
        // SimpleDialog dialog = new SimpleDialog(getActivity());
        // dialog.setTitle(getString(R.string.add_new_base_bill));
        // dialog.show();
    }

    private void setupView() {
        // mAddBill = (FloatingActionButton) mViewGroup.findViewById(R.id.add_bill);
        // mAddBill.setOnClickListener(this);
    }

    private void initAddButton() {
        final ImageView addButton = new ImageView(getActivity());
        addButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_action_new_light));
        final FloatingActionButton rightLowerButton = new FloatingActionButton.Builder(getActivity())
                .setContentView(addButton).build(FloatingActionButton.POSITION_BOTTOM_CENTER);

        SubActionButton.Builder subBuilder = new SubActionButton.Builder(getActivity());
        mAddBill = new ImageView(getActivity());
        ImageView icon2 = new ImageView(getActivity());

        mAddBill.setImageDrawable(getResources().getDrawable(R.drawable.add_bill));
        icon2.setImageDrawable(getResources().getDrawable(R.drawable.add_detail));

        final FloatingActionMenu rightLowerMenu =
                new FloatingActionMenu.Builder(getActivity()).addSubActionView(subBuilder.setContentView(mAddBill).build())
                        .addSubActionView(subBuilder.setContentView(icon2).build()).attachTo(rightLowerButton).build();

        rightLowerMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees clockwise
                addButton.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(addButton, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu menu) {
                // Rotate the icon of rightLowerButton 45 degrees counter-clockwise
                addButton.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(addButton, pvhR);
                animation.start();
            }
        });

    }
}
