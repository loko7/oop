package lap5;


public class inventoryCart {
	//atibute
	private int numItem = 200; 
	private int[] item_sum = new int[numItem];
    private static int round = 0;
    private Boolean check_add = true;
  //atibute f
    private int i_show;
    private int i_get;
    private int i_swap;
	private int i_getall;
	
	//constructer
		public  inventoryCart(int num) {
			this.numItem = num;
			//Product[] proInCart = new Product [num];
		}
		//asign product
		Product[] proInCart = new Product [numItem];
				
		
    
		
		
	//method
		public void add(Product add) {
		check_add = true; //reset 
		for(int i = 0; i < round;i++) {
			if(add.get_name().equals(proInCart[i].get_name()))  { ///check in cart //string3.equals(string4) //stinr stall in different position
				item_sum[i] +=1;
				check_add = false;
				break;
			}
		}
		if(round < numItem && check_add) {
			proInCart[round] = add;
			item_sum[round] +=1;
			round++;
		}
		else if(check_add) System.out.println("Your inventory full"); //dispaly when not add
	  }
		
		
		public  void swap_index(Product swap,int index) { //move index of product
			for(i_swap = index;i_swap < round - 1;i_swap++) {
				proInCart[i_swap] = proInCart[i_swap + 1];
				item_sum[i_swap] = item_sum[i_swap + 1];
				
				//System.out.println("Check swap");
			}
			proInCart[round] = null; // reset 
			item_sum[round] = 0;
			round--;
			
		}
			
		
		
		
		public void getProduct(Product get) {
			for(i_get = 0; i_get < round;i_get++) {
				if(get.get_name().equals(proInCart[i_get].get_name()))  { ///check in cart //string3.equals(string4) //stinr stall in different position
					item_sum[i_get] -=1;
					if(item_sum[i_get] == 0) swap_index(get, i_get); /// call to delete product out of item
					break;
				}
				
			}
		}
		
		public void  getAllProduct() {
			for(i_getall = 0 ; i_getall <= round;i_getall++) {
				proInCart[i_getall] = null;
				item_sum[i_getall] = 0;
				
			}
			round = 0; // set round 0 to new product
			
		}
		
		/*public void cart_show() {
			boolean check_show = true;
			for(i_show = 0; i_show < round;i_show++) {
				System.out.print("" + (item_sum[i_show]) + " x" + " ");
				proInCart[i_show].show();
				check_show = false;
				
			}
			if(check_show) System.out.println("your inventory cart is empy");
		}*/
		
		
		//getter
		public int get_round() {
			return round;
		}
		
		public int get_itemsum(int index_itemsum) {
			return item_sum[index_itemsum];
		}
		
		public int get_prince(int index_price) {
			return proInCart[index_price].get_price(); 
		}
		
		public String get_code(int index_code) {
			return proInCart[index_code].get_code();
			
		}
		
		public String get_name(int index_name) {
			return proInCart[index_name].get_name();
		}
}
