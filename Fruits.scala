trait Fruit {
  var weight :Int =0 
  var left : Fruit = null
  var right : Fruit = null
  
  def setweight(w :Int){
    weight = w
  }
  
  def setleft(left :Fruit){
    this.left = left
  }
  
  def setright(right :Fruit){
    this.right = right
  }
  
  def getname():String
  
}

trait BigFruit extends Fruit{ def getname():String ={ "Big"}}

trait OvalFruit extends Fruit{ def getname():String ={ "Oval"}}

trait TinyFruit extends Fruit{ def getname():String ={ "Tiny"}}

trait NonOvalFruit extends Fruit{ def getname():String ={ "Non Oval"}}

trait Berries extends OvalFruit with TinyFruit {override  def getname():String ={  super[TinyFruit].getname +" "+ super[OvalFruit].getname +" Berries"}}

trait Grapes extends TinyFruit with OvalFruit{ override def getname():String ={  super[TinyFruit].getname +" "+ super[OvalFruit].getname+" Grapes"}}

class Apple extends BigFruit with OvalFruit{
  override def getname():String ={
    super[BigFruit].getname +" "+ super[OvalFruit].getname + " Apple"
  }
}

class Orange extends BigFruit with OvalFruit{
   override def getname():String ={
    super[BigFruit].getname +" "+ super[OvalFruit].getname+ " Orange"
  }
}

class RedGrapes extends Grapes{
   override def getname():String ={
    super[Grapes].getname+" RedGrapes"
  }
}

class GreenGrapes extends Grapes{
   override def getname():String ={
     super[Grapes].getname+" GreenGrapes"
  }
}

class BlueBerry extends Berries{
   override def getname():String ={
     super[Berries].getname+" BlueBerry"
  }
}

class BlackBerry extends Berries{
   override def getname():String ={
    super[Berries].getname+" BlackBerry"
  }
}

class Rhubarb extends BigFruit with NonOvalFruit{
   override def getname():String ={
    super[BigFruit].getname +" "+ super[NonOvalFruit].getname+" Rhubarb"
  }
}


class BST {
  var root : Fruit =null
  var fruitList = List[Fruit]()
  
  def insert(node : Fruit){
    if (root eq null){
       root = node
    }
    else{
      this.insertcompare(root, node, compare)
    }
    
  }
  
 def insertcompare(root:Fruit ,node:Fruit , compare:(Fruit,Fruit)=>Boolean): Unit ={
   if(compare(root,node)==true){
     if(root.right==null){
       root.setright(node)
     }
     else{
       insertcompare(root.right,node,compare)
     }
   }
   else{
     if(root.left==null){
       root.setleft(node)
     }
     else{
       insertcompare(root.left,node,compare)
     }
   }
   
 }
  
  def compare (node1 : Fruit , node2:Fruit): Boolean={
    if(node2.weight>=node1.weight){
      return true;
    }
    else{
      return false;
    }
  }
  
  def Iterate(){
      fruitList=Nil
   iterator(root)
   fruitList = fruitList.reverse
   fruitList.foreach(fruitoflist => println("I am " +fruitoflist.getname()+ " and my weight is " + fruitoflist.weight))
    
  }
  
  def iterator(node : Fruit){
   if(node != null){
     iterator(node.left)
     fruitList = node :: fruitList
     iterator(node.right)
     
   }
    
  }
  
  def findheaviest(){
    var node = root
    while(node.right!=null){
      node = node.right
    }
     println("The heaviest fruit is " +node.getname()+ " and of weight " + node.weight)
  }
  
   def findLightest(){
    var node = root
    while(node.left!=null){
      node = node.left
    }
     println("The Lightest fruit is " +node.getname()+ " and of weight " + node.weight)
  }
   
  
  def matchtype(fruit:Fruit , typeoffruit : String)= typeoffruit match{
      case "Oval" => if(fruit.isInstanceOf[OvalFruit]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Tiny" => if(fruit.isInstanceOf[TinyFruit]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Big" => if(fruit.isInstanceOf[BigFruit]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Non Oval" => if(fruit.isInstanceOf[NonOvalFruit]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Berries" => if(fruit.isInstanceOf[Berries]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Grapes" => if(fruit.isInstanceOf[Grapes]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Apple" => if(fruit.isInstanceOf[Apple]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Orange" => if(fruit.isInstanceOf[Orange]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Red Grapes" => if(fruit.isInstanceOf[RedGrapes]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Green Grapes" => if(fruit.isInstanceOf[GreenGrapes]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Blue Berry" => if(fruit.isInstanceOf[BlueBerry]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Black Berry" => if(fruit.isInstanceOf[BlackBerry]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Rhubarb" => if(fruit.isInstanceOf[Rhubarb]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case "Fruit" => if(fruit.isInstanceOf[Fruit]) println("I am " +fruit.getname()+ " and my weight is " + fruit.weight)
      case _ =>

  }

   def filterbytype(typeoffruit : String){
       fruitList=Nil
       iterator(root)
       fruitList=fruitList.reverse
      
       fruitList.foreach(fruitoflist => matchtype(fruitoflist, typeoffruit) )
   }

   def filterbyweight(weight : Int){
       fruitList=Nil
       iterator(root)
       fruitList=fruitList.reverse
      
       fruitList.foreach(fruitoflist => if(fruitoflist.weight>=weight) println("I am " +fruitoflist.getname()+ " and my weight is " + fruitoflist.weight) )
   }

   def magnifyByType(typeoffruit: String , weight : Int){
       fruitList=Nil
       iterator(root)
       fruitList=fruitList.reverse
       root=null
       fruitList.foreach(fruitoflist =>typeoffruit match{
      case "Oval" => if(fruitoflist.isInstanceOf[OvalFruit]) fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null) ; insert(fruitoflist)
      case "Tiny" => if(fruitoflist.isInstanceOf[TinyFruit]) fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null); insert(fruitoflist)
      case "Big" => if(fruitoflist.isInstanceOf[BigFruit]) fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null);insert(fruitoflist)
      case "Non Oval" => if(fruitoflist.isInstanceOf[NonOvalFruit]) fruitoflist.weight=fruitoflist.weight+weight;fruitoflist.setleft(null) ;fruitoflist.setright(null);insert(fruitoflist)
      case "Berries" => if(fruitoflist.isInstanceOf[Berries]) fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null);insert(fruitoflist)
      case "Grapes" => if(fruitoflist.isInstanceOf[Grapes]) fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null);insert(fruitoflist)
      case "Apple" => if(fruitoflist.isInstanceOf[Apple]) fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null);insert(fruitoflist)
      case "Orange" => if(fruitoflist.isInstanceOf[Orange]) fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null);insert(fruitoflist)
      case "Red Grapes" => if(fruitoflist.isInstanceOf[RedGrapes]) fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null);insert(fruitoflist)
      case "Green Grapes" => if(fruitoflist.isInstanceOf[GreenGrapes]) fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null);insert(fruitoflist)
      case "Blue Berry" => if(fruitoflist.isInstanceOf[BlueBerry]) fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null);insert(fruitoflist)
      case "Black Berry" => if(fruitoflist.isInstanceOf[BlackBerry]) fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null);insert(fruitoflist)
      case "Rhubarb" => if(fruitoflist.isInstanceOf[Rhubarb])  fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null);insert(fruitoflist)
      case "Fruit" => if(fruitoflist.isInstanceOf[Fruit]) fruitoflist.weight=fruitoflist.weight+weight; fruitoflist.setleft(null) ;fruitoflist.setright(null);insert(fruitoflist)
      case _ => fruitoflist.setleft(null) ;fruitoflist.setright(null); insert(fruitoflist)

  })
     

   }
  
}
object main {
   def main(args:Array[String]){  
      
    var t :BST= new BST()

    var a :Fruit= new Apple(); a.setweight(25)
    var b :BigFruit= new Orange(); b.setweight(20)
    var c :Fruit= new Orange(); c.setweight(36)
    var d :Berries= new BlueBerry(); d.setweight(10)
    var e :TinyFruit= new BlackBerry(); e.setweight(22)
    var f :OvalFruit= new Apple(); f.setweight(30)
    var g :Grapes= new RedGrapes(); g.setweight(40)
    var h :TinyFruit= new BlueBerry(); h.setweight(5)
    var i :Fruit= new Apple(); i.setweight(12)
    var j :Fruit= new Orange(); j.setweight(28)
    var k :Fruit= new GreenGrapes(); k.setweight(38)
    var l :Fruit= new Rhubarb(); l.setweight(48)

    t.insert(a);t.insert(b);t.insert(c);t.insert(d);t.insert(e);t.insert(f);t.insert(g);
    t.insert(h);t.insert(i);t.insert(j);t.insert(k);t.insert(l);

    t.Iterate();

    println(" ");

    t.findLightest();
    t.findheaviest();

     println("");
     println("These are the Berries");
    t.filterbytype("Berries")

     println("");
     println("These are the Apples");
    t.filterbytype("Apple")

     println("");
     println("These are Fruits greater than or equal 15");
    t.filterbyweight(15)

    println("");
     println("These are Fruits greater than or equal 40");
    t.filterbyweight(40)

    println("");
     println("These are Fruits after magnifing Tiny Fruits by 10 ");
    t.magnifyByType("Tiny", 10)
    t.Iterate()
    } 
}
