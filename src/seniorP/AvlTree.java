package seniorP;
import java.util.Objects;


public class AvlTree 
{

	private int height;
	private int Cost;
	private Timetable timetable;
	private AvlTree left;
	private AvlTree right;
	
	public AvlTree()
	{
		Cost=-1;
		timetable=null;
		left=null;
		right=null;
		height=0;
	}
	
	public AvlTree(AvlTree copy)
	{
		Cost=-copy.getCost();
		timetable=copy.getTimetable();
		left=null;
		right=null;
		height=0;
	}
	
	
	
	public void insert(int Cost, Timetable timetable)
	{
		if(this.timetable==null)
		{
			
			this.Cost=Cost;
			this.timetable=timetable;
			height=0;
			left=new AvlTree();
			right=new AvlTree();
		}
		else 
		{
			if(Cost<this.Cost)
			{
				left.insert(Cost, timetable);
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
				right.insert(Cost, timetable);
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
	public void fixTree(AvlTree tree)
	{
		if(Objects.equals(tree.left, null))
		tree.left=new AvlTree();
		if(Objects.equals(tree.right, null))
		tree.right=new AvlTree();
		
	}
	public void balance(String rotation)
	{
		
		//System.out.println("Balancing !!");
		AvlTree temp;
		switch(rotation) 
		{
		case "LR":
			//System.out.println("LR");
			temp=new AvlTree(this);
			this.setCost(this.left.getRight().getCost());
			this.setTimetable(this.left.getRight().getTimetable());
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
			temp = new AvlTree(this);
			this.setCost(this.left.getCost());
			this.setTimetable(this.left.getTimetable());
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
			temp=new AvlTree(this);
			this.setCost(this.right.getLeft().getCost());
			this.setTimetable(this.right.getLeft().getTimetable());
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
			temp = new AvlTree(this);
			this.setCost(this.right.getCost());
			this.setTimetable(this.right.getTimetable());
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
		if(left.timetable==null && right.timetable == null)
		{	
			
			return 0;
		}
		else if(left.timetable==null && right.timetable !=null)
		{
			updatedHeight = Math.abs(right.getHeight())+1;
			return updatedHeight*-1;
		}
		else if(left.timetable!=null && right.timetable == null)
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
	
	public Timetable findBestTimetable()
	{
		if(Objects.equals(this.left.getTimetable(), null))
		{
			return this.timetable;
		}
		else
		{
			return this.left.findBestTimetable();
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

	public Timetable getTimetable() {
		return timetable;
	}

	public void setTimetable(Timetable timetable) {
		this.timetable = timetable;
	}

	public AvlTree getLeft() {
		return left;
	}

	public void setLeft(AvlTree left) {
		this.left = left;
	}

	public AvlTree getRight() {
		return right;
	}

	public void setRight(AvlTree right) {
		this.right = right;
	}
	
	
}
