class MasterClass {

    public static void main(String args[]) {
        MasterClass masterClass = new MasterClass();
        masterClass.StartProgram();
    }
    private void StartProgram() {
        boolean RunGUI = true;
        /* set this to false to run in CLI mode. There will be a better way
         * to do this in the future */
        if (RunGUI) {
            GUI gui = new GUI();
            gui.StartGUI();
        }
        CLI cli = new CLI();
        cli.StartCLI();
    }
}
