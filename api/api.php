<?php

class Constants
{
    //DATABASE DETAILS
     static $DB_SERVER="localhost";
    static $DB_NAME="id10898697_new_database";
    static $USERNAME="id10898697_hamzh";
    static $PASSWORD="499P<t^K/I&oxkIQ55";


}


class Spirituality
{
	
	   public function connect()
    {
        $con=new mysqli(Constants::$DB_SERVER,Constants::$USERNAME,Constants::$PASSWORD,Constants::$DB_NAME);
        if($con->connect_error)
        {
           // echo "Unable To Connect";
            return null;
        }else
        {
            return $con;
        }
    }
	
	
	public function offers(){

   
	   $con=$this->connect();
		
		$SQL="SELECT * FROM offers";
		
        if($con != null)
        {
			
            $result=$con->query($SQL);
			
            if($result->num_rows > 0)
            {
                $spiritual_teachers=array();
				
                while($row=$result->fetch_array())
                {
                    array_push($spiritual_teachers, array("id"=>$row['id'],"description"=>$row['description'],"price"=>$row['price'],"old_price"=>$row['old_price'],"image_url"=>$row['image_url']));
                }
                print(json_encode(array_reverse($spiritual_teachers)));
            }else
            {
            }
            $con->close();

        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }
		
	}
	
	
	
	public function sections(){	
	
	   $con=$this->connect();
		
		$SQL="SELECT * FROM sections";
		
        if($con != null)
        {
			
            $result=$con->query($SQL);
			
            if($result->num_rows > 0)
            {
                $spiritual_teachers=array();
				
                while($row=$result->fetch_array())
                {
                    array_push($spiritual_teachers, array("id"=>$row['id'],"description"=>$row['description'],"image_url"=>$row['image_url'],"rank"=>$row['rank']));
                }
                print(json_encode(array_reverse($spiritual_teachers)));
            }else
            {
            }
            $con->close();

        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }
		
	}
	
	
	
	public function sub_sections(){	
	
	   $con=$this->connect();
	   
	   //$section_id = $_POST['section_id'];
	   
	   foreach (getallheaders() as $name => $value) {
		 if($name=="folder"){
			 $section_id=$value;
		 }
		}
		
		$SQL="SELECT * FROM section_$section_id";
		
        if($con != null)
        {
			
            $result=$con->query($SQL);
			
            if($result->num_rows > 0)
            {
                $spiritual_teachers=array();
				
                while($row=$result->fetch_array())
                {
                    array_push($spiritual_teachers, array("id"=>$row['id'],"description"=>$row['description'],"price"=>$row['price'],"old_price"=>$row['old_price'],"image_url"=>$row['image_url']));
                }
                print(json_encode(array_reverse($spiritual_teachers)));
            }else
            {
            }
            $con->close();

        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }
		
	}
	
	
	public function favorite(){	
	
	   $con=$this->connect();
	   
	   
	   foreach (getallheaders() as $name => $value) {
		 if($name=="folder"){
			 $section_id=$value;
		 }
		 
		 if($name=="row"){
			 $row_id=$value;
		 }
		}
		
		
		$SQL="SELECT * FROM section_$section_id WHERE id=$row_id";
		
        if($con != null)
        {
			
            $result=$con->query($SQL);
			
            if($result->num_rows > 0)
            {
                $spiritual_teachers=array();
				
                while($row=$result->fetch_array())
                {
                    array_push($spiritual_teachers, array("id"=>$row['id'],"description"=>$row['description'],"price"=>$row['price'],"old_price"=>$row['old_price'],"image_url"=>$row['image_url']));
                }
                print(json_encode(array_reverse($spiritual_teachers)));
            }else
            {
            }
            $con->close();

        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }
		
	}
	
	public function favorite_offers(){
		
		$con=$this->connect();
	   
	   
	   foreach (getallheaders() as $name => $value) {
		
		 if($name=="row"){
			 $row_id=$value;
		 }
		}
		
		
		$SQL="SELECT * FROM offers WHERE id=$row_id";
		
        if($con != null)
        {
			
            $result=$con->query($SQL);
			
            if($result->num_rows > 0)
            {
                $spiritual_teachers=array();
				
                while($row=$result->fetch_array())
                {
                    array_push($spiritual_teachers, array("id"=>$row['id'],"description"=>$row['description'],"price"=>$row['price'],"old_price"=>$row['old_price'],"image_url"=>$row['image_url']));
                }
                print(json_encode(array_reverse($spiritual_teachers)));
            }else
            {
            }
            $con->close();

        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }
		
	}
	
	public function details(){	
	
	 $con=$this->connect();
		 
		 $section=1;
		 $row=1;
		 $array_maim;
		 $array1;
		 $array2;
		 $array3;
		 $array4;
		 $array5;
		 $num_image=0;
		 
		 $array2=array("1"=>"");
		 $array3=array("2"=>"");
		 $array4=array("3"=>"");
		 $array5=array("4"=>"");
		 
		 
		  foreach (getallheaders() as $name => $value) {
		 if($name=="folder"){
			 $section=$value;
		 }
		 
		  if($name=="row"){
			 $row=$value;
		 }
		}
		 
		
		 
		 
		 $SQL="SELECT * FROM details_section_$section WHERE id=$row"; 
		 $SQL2="SELECT * FROM section_"."$section"."_image_1 WHERE id=$row";
		 $SQL_color_2="SELECT * FROM section_"."$section"."_image_2 WHERE id=$row";
		 $SQL_color_3="SELECT * FROM section_"."$section"."_image_3 WHERE id=$row";
		 $SQL_color_4="SELECT * FROM section_"."$section"."_image_4 WHERE id=$row";	
		 $SQL_color_5="SELECT * FROM section_"."$section"."_image_5 WHERE id=$row";
		 
		 $array_list=array();
		
        if($con != null)
        {
			
            $result=$con->query($SQL);
			$result2=$con->query($SQL2);
			
			//$result_color_2=$con->query($SQL_color_2);
			
			
            if($result->num_rows > 0)
            {
			   
                while($row=$result->fetch_array())
                {
					
					
					$description_string="99";
						
                    $array_maim=array("title"=>$row['title'],"description"=>$row['description'],"size_text"=>$row['size_text'],"size_1"=>$row['size_1'],"size_2"=>$row['size_2'],"size_3"=>$row['size_3'],"size_4"=>$row['size_4'],"size_5"=>$row['size_5'],"price"=>$row['price'],"old_price"=>$row['old_price'],"colors"=>$row['colors']);
					$num_image=$row['colors'];
			   }
                
            }
			
			
			
			if($result2->num_rows > 0)
            {            
				
                while($row=$result2->fetch_array())
                {
                    $array1=array("image_url_1"=>$row['image_url_1'],"image_url_2"=>$row['image_url_2'],"image_url_3"=>$row['image_url_3'],"image_url_4"=>$row['image_url_4'],"image_url_5"=>$row['image_url_5'],"image_url_6"=>$row['image_url_6'],"image_url_7"=>$row['image_url_7'],"image_url_8"=>$row['image_url_8'],"image_url_9"=>$row['image_url_9'],"image_url_10"=>$row['image_url_10'],"color"=>$row['color']);
                }
                
            }
			

			
			if($num_image>1){
				
		
				$result_color_2=$con->query($SQL_color_2);
				
				if($result_color_2->num_rows > 0)
            {    
           
                while($row=$result_color_2->fetch_array())
                {
                    $array2=array("image_2_url_1"=>$row['image_url_1'],
					"image_2_url_2"=>$row['image_url_2'],
					"image_2_url_3"=>$row['image_url_3'],
					"image_2_url_4"=>$row['image_url_4'],
					"image_2_url_5"=>$row['image_url_5'],
					"image_2_url_6"=>$row['image_url_6'],
					"image_2_url_7"=>$row['image_url_7'],
					"image_2_url_8"=>$row['image_url_8'],
					"image_2_url_9"=>$row['image_url_9'],
					"image_2_url_10"=>$row['image_url_10'],
					"color2"=>$row['color']);
                }
                
            }
				
			}
			
		
			if($num_image>2){
						
				$result_color_3=$con->query($SQL_color_3);
				
				if($result_color_3->num_rows > 0)
            {            
				
                while($row=$result_color_3->fetch_array())
                {
                   $array3=array("image_3_url_1"=>$row['image_url_1'],
					"image_3_url_2"=>$row['image_url_2'],
					"image_3_url_3"=>$row['image_url_3'],
					"image_3_url_4"=>$row['image_url_4'],
					"image_3_url_5"=>$row['image_url_5'],
					"image_3_url_6"=>$row['image_url_6'],
					"image_3_url_7"=>$row['image_url_7'],
					"image_3_url_8"=>$row['image_url_8'],
					"image_3_url_9"=>$row['image_url_9'],
					"image_3_url_10"=>$row['image_url_10'],
					"color3"=>$row['color']);				
				}
                
            }
				
			}
			
			
			if($num_image>3){
								
				$result_color_4=$con->query($SQL_color_4);
				
				if($result_color_4->num_rows > 0)
            {            
				
                while($row=$result_color_4->fetch_array())
                {
                    $array4=array("image_4_url_1"=>$row['image_url_1'],
					"image_4_url_2"=>$row['image_url_2'],
					"image_4_url_3"=>$row['image_url_3'],
					"image_4_url_4"=>$row['image_url_4'],
					"image_4_url_5"=>$row['image_url_5'],
					"image_4_url_6"=>$row['image_url_6'],
					"image_4_url_7"=>$row['image_url_7'],
					"image_4_url_8"=>$row['image_url_8'],
					"image_4_url_9"=>$row['image_url_9'],
					"image_4_url_10"=>$row['image_url_10'],
					"color4"=>$row['color']);
				}
                
            }
				
			}
			
			
			if($num_image>4){
								
				$result_color_5=$con->query($SQL_color_5);
				
				if($result_color_5->num_rows > 0)
            {            
				
                while($row=$result_color_5->fetch_array())
                {
                    $array5=array("image_5_url_1"=>$row['image_url_1'],
					"image_5_url_2"=>$row['image_url_2'],
					"image_5_url_3"=>$row['image_url_3'],
					"image_5_url_4"=>$row['image_url_4'],
					"image_5_url_5"=>$row['image_url_5'],
					"image_5_url_6"=>$row['image_url_6'],
					"image_5_url_7"=>$row['image_url_7'],
					"image_5_url_8"=>$row['image_url_8'],
					"image_5_url_9"=>$row['image_url_9'],
					"image_5_url_10"=>$row['image_url_10'],
					"color5"=>$row['color']);
				}
                
            }
				
			}
		
			
			$result_array = array_merge($array1,$array2,$array3,$array4,$array5,$array_maim);
			array_push($array_list,$result_array);
			print(json_encode(array_reverse($array_list)));
			
            $con->close();

        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }
	
	
	
	}
	
	
	public function details_offers(){
		// **************************
		
		 $con=$this->connect();
		 
		 $section=1;
		 $row=1;
		 $array_maim;
		 $array1;
		 $array2;
		 $array3;
		 $array4;
		 $array5;
		 $num_image=0;
		 
		 $array2=array("1"=>"");
		 $array3=array("2"=>"");
		 $array4=array("3"=>"");
		 $array5=array("4"=>"");
		 
		 
		  foreach (getallheaders() as $name => $value) {	 
		  if($name=="row"){
			 $row=$value;
		 }
		}
		 
		
		 
		 
		 $SQL="SELECT * FROM details_offers WHERE id=$row"; 
		 $SQL2=       "SELECT * FROM section_0_image_1 WHERE id=$row";
		 $SQL_color_2="SELECT * FROM section_0_image_2 WHERE id=$row";
		 $SQL_color_3="SELECT * FROM section_0_image_3 WHERE id=$row";
		 $SQL_color_4="SELECT * FROM section_0_image_4 WHERE id=$row";	
		 $SQL_color_5="SELECT * FROM section_0_image_5 WHERE id=$row";
		 
		 $array_list=array();
		
        if($con != null)
        {
			
            $result=$con->query($SQL);
			$result2=$con->query($SQL2);
			
		
            if($result->num_rows > 0)
            {
                
                while($row=$result->fetch_array())
                {
                    $array_maim=array("title"=>$row['title'],"description"=>$row['description'],"size_text"=>$row['size_text'],"size_1"=>$row['size_1'],"size_2"=>$row['size_2'],"size_3"=>$row['size_3'],"size_4"=>$row['size_4'],"size_5"=>$row['size_5'],"price"=>$row['price'],"old_price"=>$row['old_price'],"colors"=>$row['colors']);
					$num_image=$row['colors'];
			   }
                
            }
			
			
			
			if($result2->num_rows > 0)
            {            
				
                while($row=$result2->fetch_array())
                {
                    $array1=array("image_url_1"=>$row['image_url_1'],"image_url_2"=>$row['image_url_2'],"image_url_3"=>$row['image_url_3'],"image_url_4"=>$row['image_url_4'],"image_url_5"=>$row['image_url_5'],"image_url_6"=>$row['image_url_6'],"image_url_7"=>$row['image_url_7'],"image_url_8"=>$row['image_url_8'],"image_url_9"=>$row['image_url_9'],"image_url_10"=>$row['image_url_10'],"color"=>$row['color']);
                }
                
            }
			

			
			if($num_image>1){
				
		
				$result_color_2=$con->query($SQL_color_2);
				
				if($result_color_2->num_rows > 0)
            {    
           
                while($row=$result_color_2->fetch_array())
                {
                    $array2=array("image_2_url_1"=>$row['image_url_1'],
					"image_2_url_2"=>$row['image_url_2'],
					"image_2_url_3"=>$row['image_url_3'],
					"image_2_url_4"=>$row['image_url_4'],
					"image_2_url_5"=>$row['image_url_5'],
					"image_2_url_6"=>$row['image_url_6'],
					"image_2_url_7"=>$row['image_url_7'],
					"image_2_url_8"=>$row['image_url_8'],
					"image_2_url_9"=>$row['image_url_9'],
					"image_2_url_10"=>$row['image_url_10'],
					"color2"=>$row['color']);
                }
                
            }
				
			}
			
		
			if($num_image>2){
						
				$result_color_3=$con->query($SQL_color_3);
				
				if($result_color_3->num_rows > 0)
            {            
				
                while($row=$result_color_3->fetch_array())
                {
                   $array3=array("image_3_url_1"=>$row['image_url_1'],
					"image_3_url_2"=>$row['image_url_2'],
					"image_3_url_3"=>$row['image_url_3'],
					"image_3_url_4"=>$row['image_url_4'],
					"image_3_url_5"=>$row['image_url_5'],
					"image_3_url_6"=>$row['image_url_6'],
					"image_3_url_7"=>$row['image_url_7'],
					"image_3_url_8"=>$row['image_url_8'],
					"image_3_url_9"=>$row['image_url_9'],
					"image_3_url_10"=>$row['image_url_10'],
					"color3"=>$row['color']);				
				}
                
            }
				
			}
			
			
			if($num_image>3){
								
				$result_color_4=$con->query($SQL_color_4);
				
				if($result_color_4->num_rows > 0)
            {            
				
                while($row=$result_color_4->fetch_array())
                {
                    $array4=array("image_4_url_1"=>$row['image_url_1'],
					"image_4_url_2"=>$row['image_url_2'],
					"image_4_url_3"=>$row['image_url_3'],
					"image_4_url_4"=>$row['image_url_4'],
					"image_4_url_5"=>$row['image_url_5'],
					"image_4_url_6"=>$row['image_url_6'],
					"image_4_url_7"=>$row['image_url_7'],
					"image_4_url_8"=>$row['image_url_8'],
					"image_4_url_9"=>$row['image_url_9'],
					"image_4_url_10"=>$row['image_url_10'],
					"color4"=>$row['color']);
				}
                
            }
				
			}
			
			
			if($num_image>4){
								
				$result_color_5=$con->query($SQL_color_5);
				
				if($result_color_5->num_rows > 0)
            {            
				
                while($row=$result_color_5->fetch_array())
                {
                    $array5=array("image_5_url_1"=>$row['image_url_1'],
					"image_5_url_2"=>$row['image_url_2'],
					"image_5_url_3"=>$row['image_url_3'],
					"image_5_url_4"=>$row['image_url_4'],
					"image_5_url_5"=>$row['image_url_5'],
					"image_5_url_6"=>$row['image_url_6'],
					"image_5_url_7"=>$row['image_url_7'],
					"image_5_url_8"=>$row['image_url_8'],
					"image_5_url_9"=>$row['image_url_9'],
					"image_5_url_10"=>$row['image_url_10'],
					"color5"=>$row['color']);
				}
                
            }
				
			}
		
			
			$result_array = array_merge($array1,$array2,$array3,$array4,$array5,$array_maim);
			array_push($array_list,$result_array);
			print(json_encode(array_reverse($array_list)));
			
            $con->close();

        }else{
            print(json_encode(array("PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }
	
		
		// **************************	
	}
	
	
	 public function handleRequest() {
		 
		 
		 foreach (getallheaders() as $name => $value) {
		 if($name=="task"){
			 $task=$value;
		 }
		}
		
		if($task=="offers"){
			$this->offers();
		}
		
		if($task=="sections"){
			$this->sections();
		}
		
		if($task=="products"){
			$this->sub_sections();
		}
		
		if($task=="details"){
			$this->details();
		}
		
		if($task=="favorite"){
			$this->favorite();
		}
		
		if($task=="details_offers"){
			$this->details_offers();
		}
		
		if($task=="favorite_offers"){
			$this->favorite_offers();
		}
	
    }
}



$spirituality=new Spirituality();
$spirituality->handleRequest();

?>