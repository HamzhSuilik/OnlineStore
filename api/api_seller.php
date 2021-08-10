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
            echo "Unable To Connect88888";
            return null;
        }else
        {
            return $con;
        }
    }
	
	
	 public function insert_new_group()
    {
		
		 // INSERT
        $con=$this->connect();
        if($con != null)
        {
            // Get image name
            $image_name = $_FILES['image']['name'];
            // Get text
            $description = mysqli_real_escape_string($con, $_POST['description']);
			//$rank = mysqli_real_escape_string($con, $_POST['rank']);
            $rank =$this->max_id('sections')+1;
			
            $sql = "INSERT INTO sections (description, image_url,rank) VALUES ('$description', '$image_name','$rank')";
			
            try
            {
                $result=$con->query($sql);
                if($result)
                {
					// get group id
					$id=$this->max_id('sections');
					 
					 // create group folder
					 mkdir("all_image/$id");
					 
					// image file directory
                       $target = "all_image/$id/".basename($image_name);
					
                    if (move_uploaded_file($_FILES['image']['tmp_name'], $target)) { 
										                      
                      ;					   
		               $this->create_table($id);
					   
                    }else{
                       print(json_encode(array("message"=>"Saved But Unable to Move Image to Appropriate Folder")));
                    }
                }else
                {
                    print(json_encode(array("message"=>"Unsuccessful. Connection was successful but data could not be Inserted.")));
                }
                $con->close();
            }catch (Exception $e)
            {
                print(json_encode(array("message"=>"ERROR PHP EXCEPTION : CAN'T SAVE TO MYSQL. ".$e->getMessage())));
                $con->close();
            }
        }else{
            print(json_encode(array("message"=>"ERROR PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
        }
        
    }
	
    
	
	public function max_id($table_name){
		
		
		$con=$this->connect();		
		$SQL="SELECT * FROM $table_name WHERE id=(SELECT max(id) FROM $table_name);";		
		$result=$con->query($SQL);
		
		$row=$result->fetch_array();		
     	$num=$row['id'];
		
		$con->close();
		
		return $num ;
			
	}
	
	
	
	public function create_table($group_num){
		
		 $con=$this->connect();
		 
		 $db_name=Constants::$DB_NAME;
		 
		 if($con != null)
        {
			$sql = "CREATE TABLE `$db_name`.`section_$group_num` ( `id` INT(10) NOT NULL AUTO_INCREMENT , `description` VARCHAR(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `price` VARCHAR(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `old_price` VARCHAR(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
			$con->query($sql);
			
			$sql ="CREATE TABLE `$db_name`.`details_section_$group_num` ( `id` INT(10) NOT NULL AUTO_INCREMENT , `title` VARCHAR(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `description` TEXT CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `size_text` VARCHAR(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `size_1` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `size_2` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `size_3` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `size_4` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `size_5` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `price` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `old_price` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `colors` INT(2) NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
			$con->query($sql);
			
			$sql ="CREATE TABLE `$db_name`.`section_"."$group_num"."_image_1` ( `id` INT(10) NOT NULL AUTO_INCREMENT , `image_url_1` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_2` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_3` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_4` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_5` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_6` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_7` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_8` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_9` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_10` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `color` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";			
			$con->query($sql);
						
			$sql ="CREATE TABLE `$db_name`.`section_"."$group_num"."_image_2` ( `id` INT(10) NOT NULL AUTO_INCREMENT , `image_url_1` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_2` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_3` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_4` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_5` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_6` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_7` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_8` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_9` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_10` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `color` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
			$con->query($sql);
			
			$sql ="CREATE TABLE `$db_name`.`section_"."$group_num"."_image_3` ( `id` INT(10) NOT NULL AUTO_INCREMENT , `image_url_1` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_2` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_3` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_4` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_5` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_6` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_7` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_8` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_9` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_10` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `color` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";			
			$con->query($sql);
			
			$sql ="CREATE TABLE `$db_name`.`section_"."$group_num"."_image_4` ( `id` INT(10) NOT NULL AUTO_INCREMENT , `image_url_1` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_2` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_3` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_4` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_5` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_6` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_7` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_8` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_9` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_10` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `color` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";			
			$con->query($sql);
			
			$sql ="CREATE TABLE `$db_name`.`section_"."$group_num"."_image_5` ( `id` INT(10) NOT NULL AUTO_INCREMENT , `image_url_1` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_2` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_3` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_4` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_5` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_6` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_7` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_8` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_9` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `image_url_10` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , `color` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";			
			$con->query($sql);
			
			$con->close();
			
			print(json_encode(array("message"=>"Success")));
			
		}else{			
			print(json_encode(array("message"=>"Saved But Unable to create group of tables")));
		}
		
		
		
	}
	
	
	public function drop_group(){
		
		
		$con=$this->connect();
		$id = mysqli_real_escape_string($con, $_POST['id']);		 		 				
		
		$sql ="DROP TABLE `details_section_"."$id"."`, `section_"."$id"."`, `section_"."$id"."_image_1`, `section_"."$id"."_image_2`, `section_"."$id"."_image_3`, `section_"."$id"."_image_4`, `section_"."$id"."_image_5`;";
		
		  if($con != null)
        {
           
            
			 $result=$con->query($sql);
                if($result)
                {
				print(json_encode(array("message"=>"Success")));
			
				$sql2="DELETE FROM `sections` WHERE `sections`.`id` = "."$id".";";
				$result2=$con->query($sql2);
                if($result2)
                {
					$this->deleteDir("all_image/$id");
					
			       print(json_encode(array("message"=>"Success")));
				   
				}
				  
				}
				
				$con->close();
		}
		
	}
	
	
	
	
	public function update_group(){
		
		
		$id = $_POST['id'];		
		
		$con=$this->connect();					
		
        if($con != null)
        {

		
		$description = $_POST['description'];
		$new_image = $_POST['new_image'];
		
		$sql = "UPDATE sections SET description='$description'";
		
		
		if($new_image=="yes"){
			// Get image name
        $image_name = $_FILES['image']['name'];
			
			$sql =$sql . ", image_url='$image_name'" ;
			
		}
		
			 		
		$sql =$sql . " WHERE id='$id'" ;	
		
		
		
		// ***********************
		
		if($new_image=="yes"){
			
		// delete old image
		
		// @@@@@@@@@@@@@@@@@@@@@@@
						
		$SQL2="SELECT * FROM sections WHERE id=$id;";		
		$result2=$con->query($SQL2);		
		$row=$result2->fetch_array();		
     	$old_image_name=$row['image_url'];
		
		unlink ("all_image/"."$id"."/".$old_image_name);
				
		
		// @@@@@@@@@@@@@@@@@@@@@@@
		
		
		
		
		// new image
			
		$target = "all_image/"."$id"."/".basename($image_name);
		
		try
            {
                $result=$con->query($sql);
                if($result)
                {
                    if (move_uploaded_file($_FILES['image']['tmp_name'], $target)) {
                       print(json_encode(array("message"=>"Success")));
                    }else{
                       print(json_encode(array("message"=>"Saved But Unable to Move Image to Appropriate Folder")));
                    }
                }else
                {
                    print(json_encode(array("message"=>"Unsuccessful. Connection was successful but data could not be Inserted.")));
                }
                $con->close();
            }catch (Exception $e)
            {
                print(json_encode(array("message"=>"ERROR PHP EXCEPTION : CAN'T SAVE TO MYSQL. ".$e->getMessage())));
                $con->close();
            }
		
		
		// ***************************
		
		
		}else{
			$result=$con->query($sql);
                if($result)
                {
					
					print(json_encode(array("message"=>"Success")));
				}
				$con->close();
			
		}
		



		}else{
			 print(json_encode(array("message"=>"ERROR PHP EXCEPTION : CAN'T CONNECT TO MYSQL. NULL CONNECTION.")));
		}			
		
		
		
		
	}
	
	
	
	public function new_product(){
		
		
		$con=$this->connect();
        if($con != null)
        {
		
		// get text data
		
		$title1=$_POST['title1'];
		$title2=$_POST['title2'];
		$description=$_POST['description'];	
		$size=$_POST['size'];
		$size1=$_POST['size1'];
		$size2=$_POST['size2'];
		$size3=$_POST['size3'];
		$size4=$_POST['size4'];
		$size5=$_POST['size5'];
		
		$price=$_POST['price'];
		$old_price=$_POST['old_price'];
		$folder=$_POST['folder'];
		$color_text=$_POST['color_text'];
		$number_of_image=$_POST['number_of_image'];
		
		$number=intval($number_of_image);
		
		
		// Get image name			
		$main_image= $_FILES['main_image']['name'];
			 
		if($number>0){
			 $image_1= $_FILES['image_1']['name'];
		}
		 
		if($number>1){
			 $image_2= $_FILES['image_2']['name'];
		}

		 
		if($number>2){
			 $image_3= $_FILES['image_3']['name'];
		}

		 
		if($number>3){
			 $image_4= $_FILES['image_4']['name'];
		}

		 
		if($number>4){
			 $image_5= $_FILES['image_5']['name'];
		}

		 
		if($number>5){
			 $image_6= $_FILES['image_6']['name'];
		}

		 
		if($number>6){
			 $image_7= $_FILES['image_7']['name'];
		}

		 
		if($number>7){
			 $image_8= $_FILES['image_8']['name'];
		}

		 
		if($number>8){
			 $image_9= $_FILES['image_9']['name'];
		}

		 
		if($number>9){
			 $image_10= $_FILES['image_10']['name'];
		}

// *****************************************************************************************	
	
		if($number==1){	
			$sql_image_1 = "INSERT INTO section_"."$folder"."_image_1 (image_url_1,color)
		VALUES ('$image_1','$color_text');";
		
		}
		
		if($number==2){
			$sql_image_1 = "INSERT INTO section_"."$folder"."_image_1 (image_url_1,image_url_2,color)
		VALUES ('$image_1','$image_2','$color_text');";
		}
		
		if($number==3){
				$sql_image_1 = "INSERT INTO section_"."$folder"."_image_1 (image_url_1,image_url_2,image_url_3,color)
		VALUES ('$image_1','$image_2','$image_3','$color_text');";
		}
		
		if($number==4){
		$sql_image_1 = "INSERT INTO section_"."$folder"."_image_1 (image_url_1,image_url_2,image_url_3,image_url_4,color)
		VALUES ('$image_1','$image_2','$image_3','$image_4','$color_text');";
		}
		
		if($number==5){
				$sql_image_1 = "INSERT INTO section_"."$folder"."_image_1 (image_url_1,image_url_2,image_url_3,image_url_4,image_url_5,color)
		VALUES ('$image_1','$image_2','$image_3','$image_4','$image_5','$color_text');";
		}
		
		if($number==6){
			$sql_image_1 = "INSERT INTO section_"."$folder"."_image_1 (image_url_1,image_url_2,image_url_3,image_url_4,image_url_5,image_url_6,color)
		VALUES ('$image_1','$image_2','$image_3','$image_4','$image_5','$image_6','$color_text');";
		}
		
		
		
		if($number==7){
			$sql_image_1 = "INSERT INTO section_"."$folder"."_image_1 (image_url_1,image_url_2,image_url_3,image_url_4,image_url_5,image_url_6,color)
		VALUES ('$image_1','$image_2','$image_3','$image_4','$image_5','$image_6','$color_text');";
		}
		
		if($number==7895){
			$sql_image_1 = "INSERT INTO section_"."$folder"."_image_1 (image_url_1,image_url_2,image_url_3,image_url_4,image_url_5,image_url_6,
		image_url_7,color)
		VALUES ('$image_1','$image_2','$image_3','$image_4','$image_5','$image_6','$color_text');";
		}
		
		if($number==8){
			$sql_image_1 = "INSERT INTO section_"."$folder"."_image_1 (image_url_1,image_url_2,image_url_3,image_url_4,image_url_5,image_url_6,
		image_url_7,image_url_8,color)
		VALUES ('$image_1','$image_2','$image_3','$image_4','$image_5','$image_6','$image_7','$image_8','$color_text');";
		}
		
		if($number==9){
			$sql_image_1 = "INSERT INTO section_"."$folder"."_image_1 (image_url_1,image_url_2,image_url_3,image_url_4,image_url_5,image_url_6,
		image_url_7,image_url_8,image_url_9,color)
		VALUES ('$image_1','$image_2','$image_3','$image_4','$image_5','$image_6','$image_7','$image_8','$image_9','$color_text');";
		}
		
		if($number==10){
			$sql_image_1 = "INSERT INTO section_"."$folder"."_image_1 (image_url_1,image_url_2,image_url_3,image_url_4,image_url_5,image_url_6,
		image_url_7,image_url_8,image_url_9,image_url_10,color)
		VALUES ('$image_1','$image_2','$image_3','$image_4','$image_5','$image_6','$image_7','$image_8','$image_9','$image_10','$color_text');";
		}
		
		
		$sql_1 = "INSERT INTO section_$folder (description, price,old_price,image_url) VALUES ('$title1','$price','$old_price','$main_image');";
		
		$sql_2 = "INSERT INTO details_section_$folder (title,description,size_text,size_1,size_2,size_3,size_4,size_5,price,old_price,colors) VALUES ('$title2','$description','$size','$size1','$size2','$size3','$size4','$size5','$price','$old_price','1');";
		
	    $sql_image_2 = "INSERT INTO section_"."$folder"."_image_2 (color) VALUES (null);";
		
		$sql_image_3 = "INSERT INTO section_"."$folder"."_image_3 (color) VALUES (null);";
		
		$sql_image_4 = "INSERT INTO section_"."$folder"."_image_4 (color) VALUES (null);";
		
		$sql_image_5 = "INSERT INTO section_"."$folder"."_image_5 (color) VALUES (null);";
		
		
		$SQL="$sql_image_1 $sql_image_2 $sql_image_3 $sql_image_4 $sql_image_5";
	
	
	
			
		$result_2=$con->query($sql_2);
		
		
		if($result_2){
		$result_1=$con->query($sql_1);
		$result_3=$con->query($sql_image_1);
		$result_4=$con->query($sql_image_2);
		$result_5=$con->query($sql_image_3);
		$result_6=$con->query($sql_image_4);
		$result_7=$con->query($sql_image_5);
		}else{
			$result_1=false;
			$result_3=false;
			$result_4=false;
			$result_5=false;
			$result_6=false;
			$result_7=false;			
		}
		
		
		        
		if($result_1 and $result_2 and $result_3 and $result_4 and $result_5 and $result_6 and $result_7)
        {
			// get group id
			$id=$this->max_id("section_$folder");
             // create group folder
			mkdir("all_image/$folder/$id");
			
			// image file directory
             $target = "all_image/$folder/$id/".basename($main_image);
			 move_uploaded_file($_FILES['main_image']['tmp_name'], $target);

             if($number>0){				 
				 $target = "all_image/$folder/$id/".basename($image_1);
			     move_uploaded_file($_FILES['image_1']['tmp_name'], $target);
		     }	

             if($number>1){				 
				 $target = "all_image/$folder/$id/".basename($image_2);
			     move_uploaded_file($_FILES['image_2']['tmp_name'], $target);
		     }			 


             if($number>2){				 
				 $target = "all_image/$folder/$id/".basename($image_3);
			     move_uploaded_file($_FILES['image_3']['tmp_name'], $target);
		     }			 


             if($number>3){				 
				 $target = "all_image/$folder/$id/".basename($image_4);
			     move_uploaded_file($_FILES['image_4']['tmp_name'], $target);
		     }			 


             if($number>4){				 
				 $target = "all_image/$folder/$id/".basename($image_5);
			     move_uploaded_file($_FILES['image_5']['tmp_name'], $target);
		     }			 


             if($number>5){				 
				 $target = "all_image/$folder/$id/".basename($image_6);
			     move_uploaded_file($_FILES['image_6']['tmp_name'], $target);
		     }			 


             if($number>6){				 
				 $target = "all_image/$folder/$id/".basename($image_7);
			     move_uploaded_file($_FILES['image_7']['tmp_name'], $target);
		     }			 


             if($number>7){				 
				 $target = "all_image/$folder/$id/".basename($image_8);
			     move_uploaded_file($_FILES['image_8']['tmp_name'], $target);
		     }			 


             if($number>8){				 
				 $target = "all_image/$folder/$id/".basename($image_9);
			     move_uploaded_file($_FILES['image_9']['tmp_name'], $target);
		     }			 


             if($number>9){				 
				 $target = "all_image/$folder/$id/".basename($image_10);
			     move_uploaded_file($_FILES['image_1']['tmp_name'], $target);
		     }			 
			 

		  
			
			print(json_encode(array("message"=>"Success","id"=>"$id")));
			
					
		}
			          
			$con->close();
		}

	
	}
	
	
	
	public function upload_product_images(){
		
		$con=$this->connect();
        if($con != null)
        {
		
		
		$folder=$_POST['folder'];
		$color_text=$_POST['color_text'];
		$number_of_image=$_POST['number_of_image'];
		$id=$_POST['id'];
		$group=$_POST['color_count'];
		
		$number=intval($number_of_image);
		
		// Get image name			
		
			 
		if($number>0){
			 $image_1= $_FILES['image_1']['name'];
		}
		 
		if($number>1){
			 $image_2= $_FILES['image_2']['name'];
		}

		 
		if($number>2){
			 $image_3= $_FILES['image_3']['name'];
		}

		 
		if($number>3){
			 $image_4= $_FILES['image_4']['name'];
		}

		 
		if($number>4){
			 $image_5= $_FILES['image_5']['name'];
		}

		 
		if($number>5){
			 $image_6= $_FILES['image_6']['name'];
		}

		 
		if($number>6){
			 $image_7= $_FILES['image_7']['name'];
		}

		 
		if($number>7){
			 $image_8= $_FILES['image_8']['name'];
		}

		 
		if($number>8){
			 $image_9= $_FILES['image_9']['name'];
		}

		 
		if($number>9){
			 $image_10= $_FILES['image_10']['name'];
		}

// *****************************************************************************************
		

		
	if($number==1){			
		$sql = "UPDATE section_"."$folder"."_image_$group SET image_url_1='$image_1',color='$color_text'  WHERE id='$id'";
	}
	
	if($number==2){			
		$sql = "UPDATE section_"."$folder"."_image_$group SET image_url_1='$image_1',image_url_2='$image_2',color='$color_text'  WHERE id='$id'";
	}
	
	if($number==3){			
		$sql = "UPDATE section_"."$folder"."_image_$group SET image_url_1='$image_1',image_url_2='$image_2',image_url_3='$image_3',color='$color_text'  WHERE id='$id'";
	}
	
	if($number==4){			
		$sql = "UPDATE section_"."$folder"."_image_$group SET image_url_1='$image_1',image_url_2='$image_2',image_url_3='$image_3',image_url_4='$image_4',color='$color_text'  WHERE id='$id'";
	}
	
	if($number==5){			
		$sql = "UPDATE section_"."$folder"."_image_$group SET image_url_1='$image_1',image_url_2='$image_2',image_url_3='$image_3',image_url_4='$image_4',image_url_5='$image_5',color='$color_text'  WHERE id='$id'";
	}
		
		
	if($number==6){			
		$sql = "UPDATE section_"."$folder"."_image_$group SET image_url_1='$image_1',image_url_2='$image_2',image_url_3='$image_3',image_url_4='$image_4',image_url_5='$image_5',image_url_6='$image_6',color='$color_text'  WHERE id='$id'";
	}
	
	if($number==7){			
		$sql = "UPDATE section_"."$folder"."_image_$group SET image_url_1='$image_1',image_url_2='$image_2',image_url_3='$image_3',image_url_4='$image_4',image_url_5='$image_5',image_url_6='$image_6',image_url_7='$image_7',color='$color_text'  WHERE id='$id'";
	}
	
	if($number==8){			
		$sql = "UPDATE section_"."$folder"."_image_$group SET image_url_1='$image_1',image_url_2='$image_2',image_url_3='$image_3',image_url_4='$image_4',image_url_5='$image_5',image_url_6='$image_6',image_url_7='$image_7',image_url_8='$image_8',color='$color_text'  WHERE id='$id'";
	}
	
	if($number==9){			
		$sql = "UPDATE section_"."$folder"."_image_$group SET image_url_1='$image_1',image_url_2='$image_2',image_url_3='$image_3',image_url_4='$image_4',image_url_5='$image_5',image_url_6='$image_6',image_url_7='$image_7',image_url_8='$image_8',image_url_9='$image_9',color='$color_text'  WHERE id='$id'";
	}
	
	if($number==10){			
		$sql = "UPDATE section_"."$folder"."_image_$group SET image_url_1='$image_1',image_url_2='$image_2',image_url_3='$image_3',image_url_4='$image_4',image_url_5='$image_5',image_url_6='$image_6',image_url_7='$image_7',image_url_8='$image_8',image_url_9='$image_9',image_url_10='$image_10',color='$color_text'  WHERE id='$id'";
	}
	
	
    $int_color=intval($group);
	
	$sql2 = "UPDATE details_section_$folder SET colors='$int_color'  WHERE id='$id'";
			
		

// *****************************************************************************************
		
		
		$result=$con->query($sql);
		$result2=$con->query($sql2);
		
		if($result and $result2){
			
		     if($number>0){				 
				 $target = "all_image/$folder/$id/".basename($image_1);
			     move_uploaded_file($_FILES['image_1']['tmp_name'], $target);
		     }	

             if($number>1){				 
				 $target = "all_image/$folder/$id/".basename($image_2);
			     move_uploaded_file($_FILES['image_2']['tmp_name'], $target);
		     }			 


             if($number>2){				 
				 $target = "all_image/$folder/$id/".basename($image_3);
			     move_uploaded_file($_FILES['image_3']['tmp_name'], $target);
		     }			 


             if($number>3){				 
				 $target = "all_image/$folder/$id/".basename($image_4);
			     move_uploaded_file($_FILES['image_4']['tmp_name'], $target);
		     }			 


             if($number>4){				 
				 $target = "all_image/$folder/$id/".basename($image_5);
			     move_uploaded_file($_FILES['image_5']['tmp_name'], $target);
		     }			 


             if($number>5){				 
				 $target = "all_image/$folder/$id/".basename($image_6);
			     move_uploaded_file($_FILES['image_6']['tmp_name'], $target);
		     }			 


             if($number>6){				 
				 $target = "all_image/$folder/$id/".basename($image_7);
			     move_uploaded_file($_FILES['image_7']['tmp_name'], $target);
		     }			 


             if($number>7){				 
				 $target = "all_image/$folder/$id/".basename($image_8);
			     move_uploaded_file($_FILES['image_8']['tmp_name'], $target);
		     }			 


             if($number>8){				 
				 $target = "all_image/$folder/$id/".basename($image_9);
			     move_uploaded_file($_FILES['image_9']['tmp_name'], $target);
		     }			 


             if($number>9){				 
				 $target = "all_image/$folder/$id/".basename($image_10);
			     move_uploaded_file($_FILES['image_1']['tmp_name'], $target);
		     }		
		
		print(json_encode(array("message"=>"Success")));
		
		}
			$con->close();
		}
	}
	
	
	public function drop_product(){
		
		$con=$this->connect();
		
		
		
		  if($con != null)
        {
			$id = $_POST['row'];
            $folder =$_POST['folder'];	

			
		   
			
			$sql_1="DELETE FROM `section_$folder` WHERE `section_$folder`.`id` = "."$id".";";
			$sql_2="DELETE FROM `details_section_$folder` WHERE `details_section_$folder`.`id` = "."$id".";";
			$sql_image_1="DELETE FROM `section_"."$folder"."_image_1` WHERE `section_"."$folder"."_image_1`.`id` = "."$id".";";
			$sql_image_2="DELETE FROM `section_"."$folder"."_image_2` WHERE `section_"."$folder"."_image_2`.`id` = "."$id".";";
			$sql_image_3="DELETE FROM `section_"."$folder"."_image_3` WHERE `section_"."$folder"."_image_3`.`id` = "."$id".";";
			$sql_image_4="DELETE FROM `section_"."$folder"."_image_4` WHERE `section_"."$folder"."_image_4`.`id` = "."$id".";";
			$sql_image_5="DELETE FROM `section_"."$folder"."_image_5` WHERE `section_"."$folder"."_image_5`.`id` = "."$id".";";
			
			$result_1=$con->query($sql_1);
	        $result_2=$con->query($sql_2);			
			$result_3=$con->query($sql_image_1);
			$result_4=$con->query($sql_image_2);
			$result_5=$con->query($sql_image_3);
			$result_6=$con->query($sql_image_4);
			$result_7=$con->query($sql_image_5);
			
			
			
	
				
                if($result_1 and $result_2 and $result_3 and $result_4 and $result_5 and $result_6 and $result_7)
                {
					$this->deleteDir("all_image/$folder/$id");	
			        print(json_encode(array("message"=>"Success")));
				}
			
			$con->close();
		}
		
	}

	
	public static function deleteDir($dirPath) {
	
    if (! is_dir($dirPath)) {
        throw new InvalidArgumentException("$dirPath must be a directory");
    }
    if (substr($dirPath, strlen($dirPath) - 1, 1) != '/') {
        $dirPath .= '/';
    }
    $files = glob($dirPath . '*', GLOB_MARK);
    foreach ($files as $file) {
        if (is_dir($file)) {
            self::deleteDir($file);
        } else {
            unlink($file);
        }
    }
    rmdir($dirPath);
}

	
	
	
    public function handleRequest() {
		
			
		if (isset($_POST['new_group'])) {
            $this->insert_new_group();
		}
			 
	
		if (isset($_POST['drop_group'])) {
            $this->drop_group();
		}	

		if (isset($_POST['update_group'])) {
            $this->update_group();
		}	
		
		
		if (isset($_POST['new_product'])) {
            $this->new_product();
		}
		
		if (isset($_POST['upload_product_images'])) {
            $this->upload_product_images();
		}
		 
		 
		if (isset($_POST['drop_product'])) {
            $this->drop_product();
		}
		
		
    }
}
$spirituality=new Spirituality();
$spirituality->handleRequest();
