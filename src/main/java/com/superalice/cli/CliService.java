package com.superalice.cli;

import com.superalice.peer.Peer;
import com.superalice.peer.PeerFactory;
import com.superalice.peer.PeerType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

import static com.superalice.cli.CliConstant.*;

@Slf4j
public class CliService {

    public static void parseArguments(String[] args, String hostAddress) {
        Options options = new Options();

        options.addOption(HELP_OPTION_SHORT, HELP_OPTION_LONG, false, "Print this message");
        options.addOption(TYPE_OPTION_SHORT, TYPE_OPTION_LONG, true, getPeerTypeDescription());
        options.addOption(PORT_OPTION_SHORT, PORT_OPTION_LONG, true, getPortDescription());
        options.addOption(BOOTSTRAP_OPTION_SHORT, BOOTSTRAP_OPTION_LONG, true, getBootstrapDescription());


        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            CommandLine cmd = parser.parse(options, args);

            // Basic arguments validation
            validateArguments(cmd);

            if (cmd.hasOption(HELP_OPTION_SHORT)) {
                formatter.printHelp("superalice", options);
                return;
            }

            // Peer start up details
            String peerType = cmd.getOptionValue(TYPE_OPTION_SHORT);;
            int port = Integer.parseInt(cmd.getOptionValue(PORT_OPTION_SHORT));
            String bootstrapAddress = null;

            // Update bootstrap address if available
            if (cmd.hasOption(BOOTSTRAP_OPTION_SHORT)) {
                bootstrapAddress = cmd.getOptionValue(BOOTSTRAP_OPTION_SHORT);
            }

            Peer peer = PeerFactory.createPeer(peerType, hostAddress, port, bootstrapAddress);
            peer.startPeer();
            log.info("Starting {}", peer);
        } catch (Exception e) {
            log.error("Error parsing arguments: {}", e.getMessage());
            formatter.printHelp("superalice", options);
        }
    }

    /**
     * If help option is not provided, type and port options are mandatory.
     * if type is not BOOTSTRAP_NODE, then bootstrap option is mandatory.
     * @param cmd CommandLine object.
     */
    private static void validateArguments(CommandLine cmd) {
        if (!cmd.hasOption(HELP_OPTION_SHORT)) {
            if (!cmd.hasOption(TYPE_OPTION_SHORT) || !cmd.hasOption(PORT_OPTION_SHORT)) {
                log.error("type and port are mandatory");
                throw new IllegalArgumentException("type and port are mandatory");
            }

            String peerType = cmd.getOptionValue(TYPE_OPTION_SHORT);
            if (!PeerType.BOOTSTRAP.getName().equals(peerType)) {
                if (!cmd.hasOption(BOOTSTRAP_OPTION_SHORT)) {
                    log.error("bootstrap address is mandatory for edge and satellite peers");
                    throw new IllegalArgumentException("bootstrap address is mandatory");
                }
            }

        }
    }

    private static String getPeerTypeDescription() {
        return "Type of device - BOOTSTRAP_NODE | SATELLITE | EDGE_DEVICE";
    }

    private static String getPortDescription() {
        return "Port number";
    }

    private static String getBootstrapDescription() {
        return "Bootstrap device address - Format - <host>:<port>";
    }

}
