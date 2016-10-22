package cphbusiness.kasper.pagh.httpexample;

/**
 * Created by kaspe on 2016-10-23.
 */

public class DummyJSON
{

    private String keyAndValue1;
    private String keyAndVaulue2;

    public DummyJSON(String keyAndValue1, String keyAndValue2)
    {
        this.keyAndValue1 = keyAndValue1;
        this.keyAndVaulue2 = keyAndValue2;
    }

    public String getKeyAndValue1()
    {
        return keyAndValue1;
    }

    public void setKeyAndValue1(String keyAndValue1)
    {
        this.keyAndValue1 = keyAndValue1;
    }

    public String getKeyAndVaulue2()
    {
        return keyAndVaulue2;
    }

    public void setKeyAndVaulue2(String keyAndVaulue2)
    {
        this.keyAndVaulue2 = keyAndVaulue2;
    }
}
