/* Fruit constructor creates an object of the prototype then returns it */
function Fruit(Weight) {
    var instance = Object.create(Fruit.prototype);

    instance.weight = Weight;
    /* intialize the parent node to undefined */
    instance.parent = undefined;
    /* intialize the left node to undefined */
    instance.left = undefined;
    /* intialize the right node to undefined */
    instance.right = undefined;

    return instance;
}

Fruit.prototype.getType = function() {
    return "Fruit";
}

Fruit.prototype.Insert = function(fruit) {
    var x = this;
    var y = undefined;

    while(x != undefined) {
        y = x;
        if(fruit.weight < x.weight) {
            x = x.left;
        } else {
            x = x.right;
        }
    }

    if(y == undefined) {
        y = fruit;
    } else if(fruit.weight < y.weight) {
        y.left = fruit;
        fruit.parent = y;
    } else {
        y.right = fruit;
        fruit.parent = y;
    }
}

/* Iterate over all node in the tree and print them in an ascending order */
Fruit.prototype.Iterate = function() {
    var TreeRoot = this;
    function recursiveInorder(root) {
        if(root == undefined) {
            return;
        }
        recursiveInorder(root.left);
        console.log("Fruit Type:", root.getType(), "-", "Weight:", root.weight, ",");
        recursiveInorder(root.right);
    }
    return recursiveInorder(TreeRoot);
}

Fruit.prototype.filterByType = function(Type) {
    // Traverse the tree all the nodes of the Type passed
    function recursiveInorder(root) {
        if(root == undefined) {
            return;
        }
        recursiveInorder(root.left);
        if(root.getType() == Type) {
            console.log("Fruit Type:", root.getType(), "-", "Weight:", root.weight, ",");
        }
        recursiveInorder(root.right);
    }

    return recursiveInorder(this);
}

Fruit.prototype.filterByWeight = function(Weight) {
    // prints all nodes higher than Weight using two helper function passed by another helper function
    higherThan(ceilingNode(this, Weight), successor);
}

Fruit.prototype.magnifyByType = function(Type, Weight) {
    // Traverse the tree and magnify all the nodes of the Type passed
    function recursiveInorder(root) {
        if(root == undefined) {
            return;
        }
        recursiveInorder(root.left);
        if(root.getType() == Type) {
            root.weight += Weight;
        }
        recursiveInorder(root.right);
    }

    return recursiveInorder(this);
}

Fruit.prototype.findLightest = function() {
    var root = this;
    if(root == undefined) {
        return;
    }

    while(root.left != undefined) {
        root = root.left;
    }
    return root;
}

Fruit.prototype.findHeaviest = function () {
    var root = this;
    if(root == undefined) {
        return;
    }
    while(root.right != null) {
        root = root.right;
    }
    return root;
}

/* Helper functions  */
function ceilingNode(root, key) {
    while(root != undefined) {
        if(root.weight == key) {
            break;
        } else if(root.weight > key) {
            root = root.leftt;
        } else {
            root = root.right;
        }
    }
    return root;
}

function successor(node) {
    var p = node.parent;
    if(node.right != undefined) {
        node = node.right;
        while(node.left != undefined) {
            node = node.left;
        }
        return node;
    }

    while(p != undefined && node == p.right) {
        node = p;
        p = p.parent;
    }
    return p;
}

function higherThan(node, func) {
    var n = func(node);
    while(n != undefined) {
        console.log("Fruit Type:", n.getType(), "-", "Weight:", n.weight, ","); 
        n = func(n);
    }
}

/* ........................................................................................................ */
/* Fruits hierarchy */
// Citrus Category
function Citrus(Weight) {
    var instance = new Fruit();
    instance.weight = Weight;
    instance.getType = function() {
        return "Citrus";
    }
    return instance;s
}

function Sweet(Weight) {
    var instance = new Citrus();
    instance.weight = Weight;
    instance.getType = function() {
        return "Sweet ";
    }
    return instance;
}
function Sour(Weight) {
    var instance = new Citrus();
    instance.weight = Weight;
    instance.getType = function() {
        return "Sour ";
    }
    return instance;
}

function Orange(Weight) {
    var instance = new Sweet();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Sweet();
        let parent2 = new Citrus();
        return "Orange ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}
function Tangerine(Weight) {
    var instance = new Sweet();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Sweet();
        let parent2 = new Citrus();
        return "Tangerine ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}
function Mandarin(Weight) {
    var instance = new Sweet();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Sweet();
        let parent2 = new Citrus();
        return "Mandarin ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}
function Lemon(Weight) {
    var instance = new Sour();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Sour();
        let parent2 = new Citrus();
        return "Lemon ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}
function Grapefruit(Weight) {
    var instance = new Sour();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Sour();
        let parent2 = new Citrus();
        return "Grapefruit ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}

// Melons Category
function Melon(Weight) {
    var instance = new Fruit();
    instance.weight = Weight;
    instance.getType = function() {
        return "Melon";
    }
    return instance;
}

function Watermelon(Weight) {
    var instance = new Melon();
    instance.weight = Weight;
    instance.getType = function() {
        let parent = new Melon();
        return "Watermelon ".concat(parent.getType());
    }
    return instance;
}
function Cantaloupe(Weight) {
    var instance = new Melon();
    instance.weight = Weight;
    instance.getType = function() {
        let parent = new Melon();
        return "Cantaloupe ".concat(parent.getType());
    }
    return instance;
}
function Galiamelon(Weight) {
    var instance = new Melon();
    instance.weight = Weight;
    instance.getType = function() {
        let parent = new Melon();
        return "Galiamelon ".concat(parent.getType());
    }
    return instance;
}

// Tropical fruit Category
function Tropical(Weight) {
    var instance = new Fruit();
    instance.weight = Weight;
    instance.getType = function() {
        return "Tropical"
    }
    return instance;
}

function Oval(Weight) {
    var instance = new Tropical();
    instance.weight = Weight;
    instance.getType = function() {
        return "Oval ";
    }
    return instance;
}
function Curved(Weight) {
    var instance = new Tropical();
    instance.weight = Weight;
    instance.getType = function() {
        return "Curved ";
    }
    return instance;
}
function Round(Weight) {
    var instance = new Tropical();
    instance.weight = Weight;
    instance.getType = function() {
        return "Round ";
    }
    return instance;
}

function Avocado(Weight) {
    var instance = new Oval();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Oval();
        let parent2 = new Tropical();
        return "Avocado ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}
function Pineapple(Weight) {
    var instance = new Oval();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Oval();
        let parent2 = new Tropical();
        return "Pineapple ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}
function Banana(Weight) {
    var instance = new Curved();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Curved();
        let parent2 = new Tropical();
        return "Banana ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}
function Coconut(Weight) {
    var instance = new Round();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Round();
        let parent2 = new Tropical();
        return "Coconut ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}
function Dragonfruit(Weight) {
    var instance = new Round();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Round();
        let parent2 = new Tropical();
        return "Dragonfruit ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}

// Berries Category
function Berry(Weight) {
    var instance = new Fruit();
    instance.weight = Weight;
    instance.getType = function() {
        return "Berry";
    }
    return instance;
}

function Tiny(Weight) {
    var instance = new Berry();
    instance.weight = Weight;
    instance.getType = function() {
        return "Tiny ";
    }
    return instance;
}
function Small(Weight) {
    var instance = new Berry();
    instance.weight = Weight;
    instance.getType = function() {
        return "Small ";
    }
    return instance;
}
function Medium(Weight) {
    var instance = new Berry();
    instance.weight = Weight;
    instance.getType = function() {
        return "Medium ";
    }
    return instance;
}

function Elderberry(Weight) {
    var instance = new Tiny();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Tiny();
        let parent2 = new Berry();
        return "ElderBerry ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}
function Blueberry(Weight) {
    var instance = new Small();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Small();
        let parent2 = new Berry();
        return "Blueberry ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}
function Huckleberry(Weight) {
    var instance = new Small();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Small();
        let parent2 = new Berry();
        return "Huckleberry ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}
function Blackberry(Weight) {
    var instance = new Medium();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Medium();
        let parent2 = new Berry();
        return "Blackberry ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}
function Raspberry(Weight) {
    var instance = new Medium();
    instance.weight = Weight;
    instance.getType = function() {
        let parent1 = new Medium();
        let parent2 = new Berry();
        return "Raspberry ".concat(parent1.getType(), parent2.getType());
    }
    return instance;
}

// Stone fruit Category
function Stonefruit(Weight) {
    var instance = new Fruit();
    instance.weight = Weight;
    instance.getType = function() {
        return "Stonefruit";
    }
    return instance;
}

function Plum(Weight) {
    var instance = new Stonefruit();
    instance.weight = Weight;
    instance.getType = function() {
        let parent = new Stonefruit();
        return "Plum ".concat(parent.getType());
    }
    return instance;
}
function Apricot(Weight) {
    var instance = new Stonefruit();
    instance.weight = Weight;
    instance.getType = function() {
        let parent = new Stonefruit();
        return "Apricot ".concat(parent.getType());
    }
    return instance;
}
function Nectarine(Weight) {
    var instance = new Stonefruit();
    instance.weight = Weight;
    instance.getType = function() {
        let parent = new Stonefruit();
        return "Nectarine ".concat(parent.getType());
    }
    return instance;
}
function Peach(Weight) {
    var instance = new Stonefruit();
    instance.weight = Weight;
    instance.getType = function() {
        let parent = new Stonefruit();
        return "Peach ".concat(parent.getType());
    }
    return instance;
}
