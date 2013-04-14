package durbin.util;


/***********************************
* Support for accessing a table one row at a time, accessing
* fields by name.  
* 
*  new OnlineTable(fileName).eachRow{row->
*		println row.name
*   println row.address
* }
* 
*/
class OnlineTable{			

	String fileName
	String sep = null;
	
	def headings
	def row

	def OnlineTable(String f,String delimiter){
		fileName = f
		sep = delimiter		
		new File(fileNAme).withReader{r->
			def headingStr = r.readLine()
			headings = headingStr.split(sep,-1)
		}

	}

	def OnlineTable(String f){
		sep = FileUtils.determineSeparator(f)a
		fileName = f
	}			

	def eachRow(Closure c){
		new File(fileName).withReader{r->
			def temp = r.readLine() // consume header.
			r.eachLine{rowStr->
				def rfields = rowStr.split(sep,-1)
				row = [:]
				rfields.eachWithIndex{f,i->row[headings[i]]=f}
				c(row)
			}
		}
	}	
	
	def headings(){
		return(headings)
	}	
}