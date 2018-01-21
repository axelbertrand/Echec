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
    
    @Override
    public boolean equals(Object o)
    {
        if(this == o)
            return true;
    	
        if(o instanceof Vector2)
        {
            Vector2 v = (Vector2)o;
            return (this.x == v.x && this.y == v.y);
        }
        else
        {
            return false;
        }
    }
    
    public int x = 0;
    public int y = 0;
}
