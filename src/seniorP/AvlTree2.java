package seniorP;

import java.util.Objects;

public class AvlTree2 {
		
	
	private int height;
	private int Cost;
	private Lecturer lecturer;
	private AvlTree2 left;
	private AvlTree2 right;
	
	public AvlTree2()
	{
		Cost=-1;
		lecturer=null;
		left=null;
		right=null;
		height=0;
	}
	public AvlTree2(AvlTree2 copy)
	{
		this.height=copy.getHeight();
		this.Cost=copy.getCost();
		this.lecturer=copy.getLecturer();
		this.left=copy.getLeft();
		this.right=copy.getRight();
	}
	
	
	
	
	
	public void insert(int Cost, Lecturer lecturer)
	{
		if(this.lecturer==null)
		{
			
			this.Cost=Cost;
			this.lecturer=lecturer;
			height=0;
			left=new AvlTree2();
			right=new AvlTree2();
		}
		else 
		{
			if(Cost<this.Cost)
			{
				left.insert(Cost, lecturer);
				height=updateHeight();
				//System.out.println(height);
				if(height==2)
				{
					if(left.getHeight()<0)
					{
						balance("LR");
					}
					else
					{
						balance("LL");
					}
				}
			}
			else
			{	
				right.insert(Cost, lecturer);
				height=updateHeight();
				//System.out.println(height);
				if(height==-2)
				{
					if(right.getHeight()>0)
					{
						balance("RL");
					}
					else
					{
						balance("RR");
					}
				}
			}
		}
		
	}
	public void fixTree(AvlTree2 tree)
	{
		if(Objects.equals(tree.left, null))
		tree.left=new AvlTree2();
		if(Objects.equals(tree.right, null))
		tree.right=new AvlTree2();
		
	}
	public void balance(String rotation)
	{
		
		//System.out.println("Balancing !!");
		AvlTree2 temp;
		switch(rotation) 
		{
		case "LR":
			//System.out.println("LR");
			temp=new AvlTree2(this);
			this.setCost(this.left.getRight().getCost());
			this.setLecturer(this.left.getRight().getLecturer());
			this.setRight(temp);
			fixTree(this.getRight());
			fixTree(this.getLeft());
			this.right.setHeight(this.right.updateHeight());
			this.left.setHeight(this.left.updateHeight());
			this.setHeight(this.updateHeight());
			//System.out.println("LR BALANCED!");
			
			break;
		case "LL":
			//System.out.println("LL");
			temp = new AvlTree2(this);
			this.setCost(this.left.getCost());
			this.setLecturer(this.left.getLecturer());
			this.setLeft(this.left.getLeft());
			this.setRight(temp);
			fixTree(this.getRight());
			fixTree(this.getLeft());
			this.right.setHeight(this.right.updateHeight());
			this.left.setHeight(this.left.updateHeight());
			this.setHeight(this.updateHeight());
			//System.out.println("LL BALANCED !!");
			break;
		case "RL":
			//System.out.println("RL");
			temp=new AvlTree2(this);
			this.setCost(this.right.getLeft().getCost());
			this.setLecturer(this.right.getLeft().getLecturer());
			this.setLeft(temp);
			fixTree(this.getRight());
			fixTree(this.getLeft());
			this.right.setHeight(this.right.updateHeight());
			this.left.setHeight(this.left.updateHeight());
			this.setHeight(this.updateHeight());
			//System.out.println("RL BALANCED!");
			break;
		case "RR":
			
			//System.out.println("RR");
			temp = new AvlTree2(this);
			this.setCost(this.right.getCost());
			this.setLecturer(this.right.getLecturer());
			this.setLeft(this.right.getLeft());
			this.setLeft(temp);
			fixTree(this.getRight());
			fixTree(this.getLeft());
			this.right.setHeight(this.right.updateHeight());
			this.left.setHeight(this.left.updateHeight());
			this.setHeight(this.updateHeight());
			//System.out.println("RR balanced");
			
			break;
		default:
			System.out.println("WRONG");
		}
	}
	public int updateHeight()
	{	
		int updatedHeight;
		if(left.lecturer==null && right.lecturer == null)
		{	
			
			return 0;
		}
		else if(left.lecturer==null && right.lecturer !=null)
		{
			updatedHeight = Math.abs(right.getHeight())+1;
			return updatedHeight*-1;
		}
		else if(left.lecturer!=null && right.lecturer == null)
		{
			updatedHeight = Math.abs(left.getHeight()) +1;
			return updatedHeight;
		}
		else
		{
			updatedHeight = Math.max(Math.abs(left.getHeight()), Math.abs(right.getHeight()));
			return updatedHeight;
			
		}
	}
	public Lecturer find(int id)
	{	
		if(this.getCost()==-1)
		{
			return null;
		}
		else if(id==this.getCost())
		{
			return this.lecturer;
		}
		else if(id<this.getCost()){
			
			return this.left.find(id);
		}
		else 
		{
			return this.right.find(id);
		}
		
		
		
	}
	public Lecturer findBestLecturer()
	{
		if(Objects.equals(this.left.getLecturer(), null))
		{
			return this.lecturer;
		}
		else
		{
			return this.left.findBestLecturer();
		}
	}
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCost() {
		return Cost;
	}

	public void setCost(int cost) {
		Cost = cost;
	}

	

	public Lecturer getLecturer() {
		return lecturer;
	}
	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}
	public AvlTree2 getLeft() {
		return left;
	}

	public void setLeft(AvlTree2 left) {
		this.left = left;
	}

	public AvlTree2 getRight() {
		return right;
	}

	public void setRight(AvlTree2 right) {
		this.right = right;
	}
	
	
	
}
