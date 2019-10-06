package com.example.starbuzz;

public class Drink {
    private String name;
    private  String description;
    private int imageResourceId;

    public static final Drink[] drinks={
            new Drink("latte","An espresso shot with steamed milk",R.drawable.latte),
            new Drink("Cappuccino","Espresso,hot milk,and steamed milk,foam",R.drawable.cappuccino),
            new Drink("Filter coffee","high quality roasted beans, brewed fresh",R.drawable.filter)
    };

    private Drink(String name,String description,int imageResourceId){
      this.name = name;
      this.description = description;
      this.imageResourceId = imageResourceId;
    }
    public String getDescription() {
        return description;
    }
    public String getName(){
        return name;
    }
    public int getImageResourceId(){
        return imageResourceId;
    }
    public String toString(){
        return this.name;
    }
}
