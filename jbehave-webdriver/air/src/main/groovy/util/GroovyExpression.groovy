package util

class GroovyExpression {

    String expression
    GroovyShell shell

    GroovyExpression(String expression) {
        this.shell = new GroovyShell()
        this.expression = expression
    }

    def execute() {
        return shell.evaluate(expression);
    }
}
