const slash = '/';
const currentDot = '.';
const up = "..";

var simplifyPath = function(path) {
    const splitedArr = path.split("/").filter(path => path !== currentDot);

    const res = [];
    const upStack = [];

    while(splitedArr.length > 0) {
        const popped =  splitedArr.pop();

        if(popped === '') {
            continue;
        }

        if(popped === up) {
            upStack.push(up);
            continue;
        }

        if(upStack.length > 0) {
            upStack.pop();
            continue;
        }
       
       res.push(popped);
    }
    
    return "/" + res.reverse().join("/");
};
