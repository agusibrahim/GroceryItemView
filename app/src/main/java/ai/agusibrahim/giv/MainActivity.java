package ai.agusibrahim.giv;
/*
Agus Ibrahim
http://fb.me/mynameisagoes
*/
import android.app.*;
import android.os.*;
import android.widget.*;
import java.util.concurrent.ThreadLocalRandom;
import android.graphics.BitmapFactory;
import android.view.*;
import android.graphics.Bitmap;
import android.view.ViewGroup.*;
import android.util.*;
import android.graphics.drawable.TransitionDrawable;
import android.view.animation.*;

public class MainActivity extends Activity implements View.OnClickListener
{
	ImageView prod_image,cart_image;
	TextView prod_harga, prod_quantity, cart_value, cart_price, cart_sub;
	View prod_card,quantity_view;
	Button btn_beli;
	ImageButton btn_up, btn_down, cart_up, cart_down;
	int curvalue=0;
	int harga=4500;
	int transduration=200;
	private TransitionDrawable prod_selectable;
	private View cart_view;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		prod_card=findViewById(R.id.produkCardView);
		btn_beli=(Button) findViewById(R.id.product_beli);
		prod_harga=(TextView) findViewById(R.id.product_harga);
		btn_up=(ImageButton) findViewById(R.id.product_quantity_plus);
		btn_down=(ImageButton) findViewById(R.id.product_quantity_min);
		prod_quantity=(TextView) findViewById(R.id.product_quantity_value);
		prod_image=(ImageView) findViewById(R.id.product_image);
		cart_image=(ImageView) findViewById(R.id.cart_image);
		cart_up=(ImageButton) findViewById(R.id.cart_up);
		cart_down=(ImageButton) findViewById(R.id.cart_down);
		cart_value=(TextView) findViewById(R.id.cart_val);
		cart_sub=(TextView) findViewById(R.id.cart_subtitle);
		cart_view=findViewById(R.id.cart_view);
		cart_price=(TextView) findViewById(R.id.cart_price);
		prod_selectable=(TransitionDrawable) findViewById(R.id.product_selectable).getBackground();
		quantity_view=findViewById(R.id.product_quantity_view);
		
		// resize card depending your screen size
		DisplayMetrics dm=getResources().getDisplayMetrics();
		ViewGroup.LayoutParams pclp=prod_card.getLayoutParams();
		pclp.height=(int)Math.round(dm.heightPixels/2.7);
		pclp.width=(int)Math.round(dm.widthPixels/2.7);
		prod_card.setLayoutParams(pclp);
		
		// initial value
		cart_view.setVisibility(View.GONE);
		cart_price.setText(priceFormater(harga));
		cart_sub.setText(String.format("30g • %s",priceFormater(harga)));
		prod_harga.setText(priceFormater(harga));
		
		// click action
		btn_beli.setOnClickListener(this);
		btn_up.setOnClickListener(this);
		btn_down.setOnClickListener(this);
		cart_up.setOnClickListener(this);
		cart_down.setOnClickListener(this);
		
    }
	
	@Override
	public void onClick(View p1) {
		if(p1==cart_down||p1==btn_down){
			if(curvalue<1) return;
			curvalue--;
			// uncomment to enable extra animation
			//animateIt(prod_quantity, true, false);
		}else if(p1==cart_up||p1==btn_up){
			curvalue++;
			// uncomment to enable extra animation
			//animateIt(prod_quantity, false, false);
		}else if(p1==btn_beli){
			curvalue=1;
			// uncomment to enable extra animation
			//animateIt(prod_quantity, false, false);
		}
		updateSelection();
	}
	
	private void updateSelection(){
		animateIt(cart_value, false, true);
		prod_quantity.setText(""+curvalue);
		cart_value.setText(""+curvalue);
		cart_price.setText(priceFormater(harga*curvalue));
		if(curvalue==1&&btn_beli.getVisibility()==View.VISIBLE){
			prod_selectable.startTransition(transduration);
			cart_view.setVisibility(View.VISIBLE);
			quantity_view.setVisibility(View.VISIBLE);
			btn_beli.setVisibility(View.GONE);
		}else if(curvalue<1){
			prod_selectable.reverseTransition(transduration);
			cart_view.setVisibility(View.GONE);
			new Handler().postDelayed(new Runnable(){
					@Override
					public void run() {
						quantity_view.setVisibility(View.GONE);
						btn_beli.setVisibility(View.VISIBLE);
					}
				}, transduration);
		}
	}
	private void animateIt(View v, boolean reverse, boolean fade){
		v.animate().setDuration(0);
		if(fade) v.animate().alpha(0);
		else v.animate().translationY(reverse?100:-100);
		v.animate().start();
		v.animate().setDuration(fade?1000:500);
		if(fade) v.animate().alpha(1);
		else v.animate().translationY(0);
		v.animate().start();
	}
	public String priceFormater(int s){
		return ("Rp"+s).replaceAll("(\\d)(?=(\\d{3})+(?!\\d))", "$1.");
	}

	@Override
	protected void onResume() {
		super.onResume();
		// trying to randomize display image
		int randint = ThreadLocalRandom.current().nextInt(0, 3 + 1);
		int resId = getResources().getIdentifier("p"+randint, "raw", getPackageName());
		Bitmap prodimg=BitmapFactory.decodeResource(getResources(), resId);
		prod_image.setImageBitmap(prodimg);
		cart_image.setImageBitmap(prodimg);
	}
}
