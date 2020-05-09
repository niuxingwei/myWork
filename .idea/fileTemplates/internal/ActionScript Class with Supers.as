#parse("ActionScript File Header.as")
/** *@ClassName ${NAME} 
*@Description TODO 
*@Author ${USER} 
*@Date ${DATE} ${TIME} 
*@Version 1.0 
*/
package ${PACKAGE_NAME}#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "") #end{
${Access_modifier} class ${NAME} #if (${Superclass} && ${Superclass} != "")extends ${Superclass}#end #if (${SuperInterfaces} && ${SuperInterfaces} != "")implements #foreach( $i in ${SuperInterfaces} ) #if ($foreach.count > 1),#end $i #end#end{
    public function ${NAME}() {
        #if (${Superclass} && ${Superclass} != "")super();#end
    }
}
}
