import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncodingAttributes;
import java.io.File;
import java.util.LinkedList;

public class WavToMp3 {
    /**
     * 执行转化过程
     *
     * @param source
     *            源文件
     * @param desFileName
     *            目标文件名
     * @return 转化后的文件
     */
    public static File execute(File source, String desFileName)
            throws Exception {
        File target = new File(desFileName);
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(36000)); //音频比率 MP3默认是1280000
        audio.setChannels(new Integer(2));
        audio.setSamplingRate(new Integer(44100));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(source, target, attrs);
        return target;
    }

    public static void traverseFolder1(String path,String newPath) throws Exception {
        int fileNum = 0, folderNum = 0;
        File file = new File(path);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);
                    fileNum++;
                } else {
                    String Pathname = file2.getAbsolutePath();
                    System.out.println("文件:" + Pathname);
                    //
                    String FileSuffix = Pathname.substring(Pathname.lastIndexOf(".")+1);

                    if (FileSuffix.toLowerCase().equals("wav")){
                        File file1 = new File(Pathname);
                        String Filename = Pathname.substring(0,Pathname.lastIndexOf("."));
                        String Filename1 = Filename.substring(Filename.lastIndexOf("\\")+1);
                        System.out.println("名字:" + Filename1+"--"+"后缀："+FileSuffix);
                        execute(file1,  newPath+Filename1+".mp3");
                        file1.delete();
                        folderNum++;

                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        System.out.println("共处理文件:" + folderNum + ",文件夹共有:" + fileNum);

    }

    public static void main(String[] args) throws Exception {
        traverseFolder1("I:\\WavAudio\\audio","I:\\audio\\");
    }

}
