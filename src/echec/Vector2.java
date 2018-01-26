/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package echec;

/**
 *
 * @author Axel
 */
public class Vector2
{
    public Vector2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public Vector2(Vector2 v)
    {
        this.x = v.x;
        this.y = v.y;
    }
    
    public void add(Vector2 v)
    {
        this.x += v.x;
        this.y += v.y;
    }
    
    public static Vector2 add(Vector2 v1, Vector2 v2)
    {
        Vector2 v = new Vector2(v1);
        v.x += v2.x;
        v.y += v2.y;
        
        return v;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
    	
        if(o == null || !(o instanceof Vector2))
            return false;
        
        Vector2 v = (Vector2)o;
        return (this.x == v.x && this.y == v.y);
    }
    
    @Override
    public String toString()
    {
        return "(" + this.x + ", " + this.y + ")";
    }
    
    public int x = 0;
    public int y = 0;
}
