import java.awt.TextArea;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import org.apache.commons.io.FileUtils;


public class Sub {
	public static void CopyFilesByFolder(String Source , String Target , TextArea txtAreaLog){
		String[] FileList = Sub.FindSubFiles(Source);
		//System.out.println("There are " + FileList.length + " files Under " + Source);
		for(int a=0 ; a<FileList.length ; a++){
			//System.out.println("File : " + FileList[a]);
			File FileSource = new File(Source + "\\" + FileList[a]);
			File FileTarget = new File(Target + "\\" + FileList[a]);
			if(!FileTarget.exists()){
				Sub.CopyFile(FileSource, FileTarget, txtAreaLog);				
			}			
		}
		
		String[] FolderList = Sub.FindSubFolders(Source);
		//System.out.println("There are " + FolderList.length + " folders Under " + Source);
		for(int a=0 ; a<FolderList.length ; a++){
			String newSource = Source + "\\" + FolderList[a];
			String newTarget = Target + "\\" + FolderList[a];
			new File(newTarget).mkdirs();
			//System.out.println(newSource + " to " + newTarget);
			Sub.CopyFilesByFolder(newSource, newTarget, txtAreaLog);
		}
		
	}
	
	private static void CopyFile(File source, File dest, TextArea txtAreaLog){
		try {
			txtAreaLog.append("Copying : " + String.valueOf(source) + "\n");// + "\nto : " + String.valueOf(dest) + "\n");
			txtAreaLog.getGraphics();
			txtAreaLog.append("to : " + String.valueOf(dest) + "\n");
			txtAreaLog.getGraphics();
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	public static String[] FindSubFolders(String Folder){
		//System.out.println("FindSubFolders : " + Folder);
		File file = new File(Folder);
		String[] SubFolder = file.list(new FilenameFilter() {
		  public boolean accept(File current, String name) {
			  return new File(current, name).isDirectory();
		  }
		});
		return SubFolder;
	}
	public static String[] FindSubFiles(String Folder){
		//System.out.println("FindSubFiles : " + Folder);
		File file = new File(Folder);
		String[] SubFiles = file.list(new FilenameFilter() {
			  public boolean accept(File current, String name) {
			    return new File(current, name).isFile();
			  }
			});
		return SubFiles;
		}
	

}
