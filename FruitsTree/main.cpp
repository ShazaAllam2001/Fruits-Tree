#include <iostream>
#include <typeinfo>
#include <string.h>
using namespace std;

//                                  fruit
// oval                     tiny           non tiny         non oval
//       berries     grapes                         rhubarb
//      blue,black    red,yellow
//oval and non tiny : apple , orange , pomegranate

class fruit {
    private:
        int weight;
    public:
        int getWeight(){
            return weight;
        }
        void setWeight(int n){
            this->weight=n;
        }
        virtual string getType()=0;
};
class oval: virtual public fruit {
    public :
        virtual string getType(){
            return "oval fruit";
        }
};
class nonOval : virtual public fruit{
     public :
        virtual string getType(){
            return "non oval fruit";
        }
};
class tiny : virtual public fruit{
     public :
        virtual string getType(){
            return "tiny fruit";
        }
};
class nonTiny :virtual public fruit{
     public :
        virtual string getType(){
            return "non tiny fruit";
        }
};
class apple :public oval,public nonTiny{
    public:
        string getType(){//no children for apple so no need for virtual
            string s="apple "+(oval::getType())+" "+nonTiny::getType();
            return s;
        }
};
class orange : public oval,public nonTiny{
    public:
        string getType(){//no children for orange so no need for virual
            string s="orange "+(oval::getType())+" "+nonTiny::getType();
            return s;
        }
};
class pomegranate : public oval,public nonTiny{
    public:
        string getType(){//no children for pomegranate so no need for virtual
            string s="pomegranate "+(oval::getType())+" "+nonTiny::getType();
            return s;
        }
};
class grapes : public oval,public tiny{
     public:
        virtual string getType(){
            string s="grapes "+(oval::getType())+" "+tiny::getType();
            return s;
        }
};
class redGrapes : public grapes{
     public:
        string getType(){//no children for red grapes so no need for virtual
            string s="red grapes "+grapes::getType();
            return s;
        }
};
class yellowGrapes : public grapes{
    public:
        string getType(){//no children for yellow grapes so no need for virtual
            string s="yellow grapes "+grapes::getType();
            return s;
        }
};
class berries : public oval,public tiny{
    public:
        virtual string getType(){
            string s="berries "+oval::getType()+" "+tiny::getType();
            return s;
        }
};
class blueBerries : public berries{
    public:
        string getType(){//no children for blue berries so no need for virtual
            string s="blue berries "+berries::getType();
            return s;
        }
};
class blackBerries : public berries{
    public:
        string getType(){//no children for black berries so no need for virtual
            string s="black berries "+berries::getType();
            return s;
        }
};
class rhubarb : public nonOval,public nonTiny{//nonOval and not tiny
    public:
        string getType(){//no children for rhubarb so no need for virtual
            string s="rhubarb "+nonOval::getType()+" "+nonTiny::getType();
            return s;
        }
};


typedef struct TreeNode {//node is object of fruit it means it is fruit the attributes which are more than that is left , right
        fruit *data;
        TreeNode* left  ;
        TreeNode* right ;
};

class BinarySearchTree{
    private:
         TreeNode *rootOfTree;
         int sizeOfTree=0;
         int flagToFilterWeight=0;
         string typeFil;
         int flagToFilterType=0;
         int w;
         int countInsertion=0;
         fruit *arrOfObjects [500];//max size =500 nodes
         void iterateOnTree(TreeNode* r){
            if(r!=NULL)
           {
               if(r->left==NULL||flagToFilterWeight==0||
                  (flagToFilterWeight==1&&r->left->data->getWeight()>w)){
                    iterateOnTree(r->left);
                }
                if((flagToFilterType==0||(flagToFilterType==1&&(r->data->getType()).find(typeFil)!=string::npos&&
                    (r->data->getType()).find("non "+typeFil)==string::npos))){
                    //if case flag = 1 check if type exists but 'not type' does n't exist
                            cout<<"Weight = "<<((r->data)->getWeight())<<" Type = "<<r->data->getType()<<"\n";
                    }
               iterateOnTree(r->right);
           }
        }

        TreeNode *newNode(fruit* f){
            TreeNode* nodePtr=new TreeNode;
            nodePtr->data=f;
            nodePtr->left=NULL;
            nodePtr->right=NULL;
        }

        TreeNode* getMin(TreeNode* root){
            while(root->left!=NULL){
                root=root->left;
            }
            return root;
        }
    public :

        int getSize(){
            return this->sizeOfTree;
        }

        TreeNode *getRoot(){
            return this->rootOfTree;
        }


        TreeNode* insertInTree(TreeNode *rootTree,fruit* f){
            if(sizeOfTree==0){
                arrOfObjects[sizeOfTree]=f;
                rootOfTree=newNode(f);
                sizeOfTree++;
                return rootOfTree;
            }else if(rootTree==nullptr){
                    arrOfObjects[sizeOfTree]=f;
                    sizeOfTree++;
                return newNode(f);
            }
            else{
                if(f->getWeight() >=((rootTree->data)->getWeight())){
                    rootTree->right=insertInTree(rootTree->right,f);
                }else {
                    rootTree->left=insertInTree(rootTree->left,f);
                }

                return rootTree;
            }

        }

        void iterate(){
            this->iterateOnTree(rootOfTree);
        }

        void filterByType(string type){
                flagToFilterType=1;
                typeFil=type;
                iterateOnTree(rootOfTree);
                flagToFilterType=0;
        }

        void filterByWeight(int Weight){//note that the concept of higher order functions is refused in c++;
            // we can use iterate method here to filter by weight
            TreeNode *r=rootOfTree;w=Weight;
            flagToFilterWeight=1;
            while(r!=NULL && (r->data->getWeight())<=Weight){
                r=r->right;
            }
            iterateOnTree(r);//it will print all nodes of this sub tree beginning from r
            flagToFilterWeight=0;
        }

        void magnifyByType(string type,int Weight){
            int i=0;
            rootOfTree->left=NULL;
            rootOfTree->right=NULL;
            while(i<sizeOfTree){
                if((arrOfObjects[i]->getType()).find(type)!=string::npos&&
                    (arrOfObjects[i]->getType()).find("non "+type)==string::npos){
                    arrOfObjects[i]->setWeight(arrOfObjects[i]->getWeight()+Weight);
                }
                i++;
            }
            int j=1;

            if(i>0){rootOfTree=insertInTree(NULL,arrOfObjects[0]);
                while(j<i){
                    insertInTree(rootOfTree,arrOfObjects[j]);
                    j++;
                }
            }
        }



        TreeNode* findHeaviest(){
            TreeNode* t;
            while(t->right!=NULL){
                t=t->right;
            }
            return t;
        }

        TreeNode* findLightest(){
            return getMin(rootOfTree);
        }

};
int main()
{
    apple a1;a1.setWeight(15);
    apple a2;a2.setWeight(25);
    orange o1;o1.setWeight(10);
    orange o2;o2.setWeight(28);
    grapes g1;g1.setWeight(27);
    redGrapes g2;g2.setWeight(221);
    fruit *app1=&a1;
    fruit *app2=&a2;
    fruit *or1=&o1;
    fruit *or2=&o2;
    fruit *gr1=&g1;
    fruit *gr2=&g2;
    BinarySearchTree b;
    TreeNode* t=b.insertInTree(NULL,app1);
    b.insertInTree(t,app2);
    b.insertInTree(t,or1);
    b.insertInTree(t,or2);
    b.insertInTree(t,gr1); b.insertInTree(t,gr2);
    b.magnifyByType("apple",10);
    b.iterate();
    return 0;
}
