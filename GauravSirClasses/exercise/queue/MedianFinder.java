class MedianFinder {
    PriorityQueue<Integer>lower;
    PriorityQueue<Integer>upper;
    public MedianFinder() {
        lower = new PriorityQueue<>((a,b)->{
	    return b-a;
	});
	upper = new PriorityQueue<>((a,b)->{
	    return a-b;
	});
    }
    
    public void addNum(int num) {
        if(lower.isEmpty()){
	    lower.add(num);
	    return;
	}
	if(lower.peek()<num){
	    upper.add(num);
	}else{
	    lower.add(num);
	}
	if(Math.abs(lower.size()-upper.size())>1){
	    shiftElements();
	}
    }
    private void shiftElements(){
        while(Math.abs(lower.size()-upper.size())>1){
	    if(upper.size()>lower.size()){
	        lower.add(upper.poll());
	    }else{
	        upper.add(lower.poll());
	    }
	}
    }
    public double findMedian() {
        if(lower.size()>upper.size()){
	    return (double)lower.peek();
	}
	if(upper.size()>lower.size()){
	    return (double)upper.peek();
	}
	return (double)(lower.peek()+upper.peek())/2.0;
    }
}
