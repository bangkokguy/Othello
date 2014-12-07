package bangkokguy.development.android.othello;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    public GridView playGround;

    private final static int cPlayerOne = 1;
    private final static int cPlayerTwo = 2;
    private final static int cEmpty = 0;

    private static final String[] cPieces  = {" ","X","O"};
    private static final int[] cImages = {R.drawable.me_8_256x256, R.drawable.ic_launcher,R.drawable.me_8_256x256};
    private static Integer[][] wPlayGround;
    private static String[] sPlayGround;
    //private static int[]tt;

    //private ArrayAdapter<String> bb;
    private MyAdapter cc;

    private void initProgram() {

        wPlayGround = new Integer[10][10];
        sPlayGround = new String[100];

        for (int i=0;i<=9;i++) {
            for (int j=0;j<=9;j++) {
                wPlayGround[i][j] = cEmpty;
            }
        }
        wPlayGround [4][4]= cPlayerOne;
        wPlayGround [5][5]= cPlayerOne;
        wPlayGround [4][5]= cPlayerTwo;
        wPlayGround [5][4]= cPlayerTwo;

    }

    private void fillScreen() {
        for (int i=0;i<=9;i++) {
            for (int j=0;j<=9;j++) {
                sPlayGround[i*10+j]=cPieces[(wPlayGround[i][j])];
            }
        }
        //bb.addAll(sPlayGround);
    }

    private void mainLogic() {
        initProgram();
        //bb = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        cc = new MyAdapter(this);
        playGround.setAdapter(cc);
        fillScreen();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playGround = (GridView)findViewById(R.id.gridView);
        playGround.setNumColumns(8);

        playGround.setOnItemClickListener(mMessageClickedHandler);

        mainLogic();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* Create a message handling object as an anonymous class.*/
    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            cc.notifyDataSetChanged();
            sPlayGround[position]="*";
            wPlayGround[5][5]=1;
            //bb.clear();
            //bb.addAll(sPlayGround);
        }
    };

    //------------------------------------
    private class MyAdapter extends BaseAdapter
    {
        private List<Item> items = new ArrayList<>();
        private LayoutInflater inflater;

        public MyAdapter(Context context)
        {
            inflater = LayoutInflater.from(context);

            for (int i=1; i<9;i++){
                for (int j=1; j<9;j++) {
                    items.add(new Item("Image 1", cImages[wPlayGround[i][j]]));
                }
            }
            /*items.add(new Item("Image 2", R.drawable.me_8_256x256));
            items.add(new Item("Image 3", R.drawable.me_8_256x256));
            items.add(new Item("Image 4", R.drawable.me_8_256x256));
            items.add(new Item("Image 5", R.drawable.me_8_256x256));
            items.add(new Item("Image 5", R.drawable.me_8_256x256));
            items.add(new Item("Image 5", R.drawable.me_8_256x256));
            items.add(new Item("Image 5", R.drawable.me_8_256x256));
            items.add(new Item("Image 5", R.drawable.me_8_256x256));
            items.add(new Item("Image 5", R.drawable.me_8_256x256));
            items.add(new Item("Image 5", R.drawable.me_8_256x256));
            items.add(new Item("Image 5", R.drawable.me_8_256x256));
            items.add(new Item("Image 5", R.drawable.me_8_256x256));
            items.add(new Item("Image 5", R.drawable.me_8_256x256));
            items.add(new Item("Image 5", R.drawable.me_8_256x256));*/
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object getItem(int i)
        {
            return items.get(i);
        }

        @Override
        public long getItemId(int i)
        {
            return items.get(i).drawableId;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup)
        {
            View v = view;
            ImageView picture;
            //TextView name;

            if(v == null)
            {
                v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
                v.setTag(R.id.picture, v.findViewById(R.id.picture));
                //v.setTag(R.id.text, v.findViewById(R.id.text));
            }

            picture = (ImageView)v.getTag(R.id.picture);
            //name = (TextView)v.getTag(R.id.text);

            Item item = (Item)getItem(i);

            picture.setImageResource(item.drawableId);
            //picture.setImageResource(R.drawable.me_8_256x256);
            //name.setText(item.name);

            return v;
        }

        private class Item
        {
            final String name;
            final int drawableId;

            Item(String name, int drawableId)
            {
                this.name = name;
                this.drawableId = drawableId;
            }
        }
    }
    //------------------------------------

}