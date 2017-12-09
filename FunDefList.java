abstract class FunDefList
{
	abstract void printParseTree(String indent);
	abstract void buildTypeMaps();
	abstract TypeVal typeEval();
}