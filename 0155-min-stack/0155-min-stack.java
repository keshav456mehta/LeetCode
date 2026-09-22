class MinStack 
{
    Stack<Integer> st;
    Stack<Integer> minSt;

    public MinStack() 
    {
        st = new Stack<Integer>();
        minSt = new Stack<Integer>();
    }
    
    public void push(int val) 
    {
        st.push(val);

        if(minSt.isEmpty() || val <= minSt.peek())
        {
            minSt.push(val);
        }
    }
    
    public void pop() 
    {
        if(st.peek().equals(minSt.peek()))
        {
            minSt.pop();
        }

        st.pop();
    }
    
    public int top() 
    {
        return st.peek();
    }
    
    public int getMin() 
    {
        return minSt.peek();
    }
}