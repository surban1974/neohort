package neohort.service.mail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataSource;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.ParseException;

public class BADataSource implements DataSource{
  private byte[] data;
  private String type;
  private String name = "";

  public BADataSource(InputStream paramInputStream, String paramString)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[8192];

    int i=0;
    
    while ((i = paramInputStream.read(arrayOfByte)) > 0)
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    this.data = localByteArrayOutputStream.toByteArray();
    this.type = paramString;
  }

  public BADataSource(byte[] paramArrayOfByte, String paramString)
  {
    this.data = paramArrayOfByte;
    this.type = paramString;
  }

  public BADataSource(String paramString1, String paramString2)
    throws IOException
  {
    String str = null;
    try {
      ContentType localContentType = new ContentType(paramString2);
      str = localContentType.getParameter("charset"); } catch (ParseException localParseException) {
    }
    if (str == null) {
      str = MimeUtility.getDefaultJavaCharset();
    }
    this.data = paramString1.getBytes(str);
    this.type = paramString2;
  }

  public InputStream getInputStream()
    throws IOException
  {
    if (this.data == null)
      throw new IOException("no data");
    return new ByteArrayInputStream(this.data);
  }

  public OutputStream getOutputStream()
    throws IOException
  {
    throw new IOException("cannot do this");
  }

  public String getContentType()
  {
    return this.type;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }
}
