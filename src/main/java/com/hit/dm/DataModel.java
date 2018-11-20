package main.java.com.hit.dm;

import java.io.Serializable;
import java.util.Comparator;

public class DataModel<T> implements Serializable,Comparable<DataModel<T>>
{
    private Long dataModelId;
    private T content;
    public DataModel(java.lang.Long id, T content)
    {
        this.dataModelId = id;
        this.content = content;
    }

    @Override
    public int compareTo(DataModel<T> o) {
        if(this.getDataModelId() > o.getDataModelId()) return 1;
       else return -1;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataModel)) {
            return false;
        }

        DataModel DM = (DataModel) obj;
        return((this.dataModelId == DM.dataModelId)&&(this.content == DM.content));
    }
    @Override
    public int hashCode() {
     return Long.hashCode(dataModelId);
    }
    public String toString()
    {
        return dataModelId + ":" + content.toString();
    }
    public Long getDataModelId()
    {return dataModelId;}
    public void setDataModelId(Long id)
    {this.dataModelId = id;}
    public T getContent()
    {return content;}
    public void setContent(T content)
    {this.content = content;}

}
