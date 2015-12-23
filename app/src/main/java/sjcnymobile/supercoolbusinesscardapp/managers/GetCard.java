package sjcnymobile.supercoolbusinesscardapp.managers;

import java.util.ArrayList;

import sjcnymobile.supercoolbusinesscardapp.DataObjects.Bizcard;

/**
 * Created by AnthonyK on 12/23/2015.
 */
public class GetCard {


    private ArrayList<ListItem> getListData() {
        ArrayList<ListItem> listMockData = new ArrayList<ListItem>();
        DatabaseManager mydbm = DatabaseManager.getInstance();
       // TextView txtMsg = (TextView)findViewById(R.id.txtMsg);
        ArrayList<Bizcard> mybizcards = new ArrayList<Bizcard>();
        mybizcards=mydbm.getAllBizCards();
        if (mybizcards.size()>0) {
            for(int i=0; i<mybizcards.size(); i++){
                ListItem newsData = new ListItem();
                newsData.setUrl( mybizcards.get(i).getImageName());
                newsData.setCompany(mybizcards.get(i).getBusinessName());
                newsData.setCompId(mybizcards.get(i).getId());
                listMockData.add(newsData);
            }
        }
       // txtMsg.setText("Record Count: " + mybizcards.size());
        return listMockData;
    }
}
