import java.util.*;

public class test<Key extends Comparable<Key>, Value> {
	Node root;

	class Node{
		Key  key;
		Value val;
		Node left, right;
		int size;

		public Node(Key key, Value val, int size){
			this.key = key;
			this.val = val;
			this.size = 1;
		}
	}

	 public test(){
		root = null;
	
	}

	public boolean isEmpty(){
		if(size()==0){
			return true;
		}
			return false;
	}

	public int size(){
		return size(root);
	}

	private int size(Node x){
		if(x==null){
			return 0;
		}
		else{
			return x.size;
		}


	}
	public Value get(Key key){
		if(key == null) throw new IllegalArgumentException("key cannot be null!");
		return get(root, key);

	 }
	private Value get(Node x, Key key){
		if(x == null){
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
		{
			return get(x.left, key);
		}
		else if(cmp > 0){
			return get(x.right, key);
		}
		else{
			return x.val;
		}

	}
	
		
	

	public void put(Key key, Value val){
		if(key == null) throw new IllegalArgumentException("key cannot be null!");
		root = put(root, key,val);
	
	}

	private Node put(Node x, Key key, Value val){
		if(x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if(cmp < 0) x.left = put(x.left, key, val);
		else if(cmp > 0) x.right = put(x.right, key, val);
		else{
			x.val = val;
		}
		x.size = size(x.left) + size(x.right) + 1;
		return x;
		
		

	}
	public Key min(){
		return min(root).key;
	}
	private Node min(Node x){
		if(x.left == null) return x;
		return min(x.left);
	}

	public Key floor(Key key) {
		Node x = floor(root, key);

		if(x==null) return null;
		return x.key;

        
    } 
    public void deleteMin() {
        
        root = deleteMin(root);
        
    }

    private Node deleteMin(Node x) {
        
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size= size(x.left) + size(x.right) + 1;
        return x;
    }
   
    public void delete(Key key) {
        root = delete(root, key);
        
    }

    private Node delete(Node x, Key key) {
       
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left,  key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else { 
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        } 
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    } 



    private Node floor(Node x, Key key) {
     	if(x==null) return null;

     	int temp = key.compareTo(x.key);

     	if(temp==0) return x;
     	if(temp<0) return floor(x.left, key);

     	Node t =floor(x.right, key);
     	if(t !=null) return t;
     	else return x;
    }
       
     
	public Key select(Key key){
		if(isEmpty()){
			throw new NoSuchElementException("calls floor() with empty symbol table");
		}
		Node curNode = root;
		Node parent = null;
		while(curNode != null){
			parent = curNode;
			int cmp = key.compareTo(parent.key);
			if(key == null){
				throw new IllegalArgumentException("calls floor() with a null key");
			}
			if(cmp > 0){
				curNode = parent.right;
			}
			//This condition is for checking floor of given key in left side
			else if(cmp < 0){
				curNode = parent.left;
				//This condition checks if key is greater than left parent then it returns the curNode 
				int cm = key.compareTo(parent.key);
				if(cm > 0 ){
					return curNode.key;
				}
				//This is for if the key is still less then left parent
				else{
					curNode = parent.left;
				} 
			}
		}
		return parent.key;
	}
	
	
    public Iterable<Key> keys(Key lo, Key hi) {
        if(lo==null || hi==null) throw new IllegalArgumentException("argument to keys() is null");

        Queue<Key> queue = new LinkedList<Key>();
        keys(root, queue, lo, hi);  
        return queue;
    } 

    private void keys(Node x,Queue<Key> queue, Key lo, Key hi) { 
        if (x == null) return;  
        
        Node temp1 = x;  
        while (temp1 != null){  
      
            int cmp = temp1.key.compareTo(hi);
            int cam = temp1.key.compareTo(lo);
            if (temp1.left == null){   
                if (cmp <= 0 && cam >= 0)  queue.offer(temp1.key);
                temp1 = temp1.right;

            }else{  
                Node temp2 = temp1.left;
            
                while (temp2.right != null && temp2.right != temp1)  
                    temp2 = temp2.right;  
        
                if (temp2.right == null){  
                    temp2.right = temp1;  
                    temp1 = temp1.left; 

                }else{  
                    temp2.right = null;    
                    if (cmp <= 0 && cam >= 0)  queue.offer(temp1.key);  
                    temp1 = temp1.right;  
                }  
            }  
        }   
    } 




	public static void main(String[] args) {
		test<String, Integer> obj = new test<>();
		obj.put("Ada",1);
		obj.put("Ballerina",3);
		obj.put("Html",5);
		obj.put("Java",7);
		obj.put("Java",8);
		obj.put("Dart",9);
		System.out.println(obj.get("Ada"));
		System.out.println(obj.get("Java"));
		System.out.println(obj.size());
		System.out.println(obj.min());
		System.out.println(obj.floor("Ballerina"));
		//System.out.println(obj.select(3));
		System.out.println(obj.keys("Ada","Java"));
		System.out.println(obj.get("Java"));
		System.out.println(obj.size());
		//System.out.println(obj.deleteMin());
		System.out.println(obj.keys("Ballerina","Java"));
	}
}