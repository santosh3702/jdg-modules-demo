#!/bin/sh 
DEMO="JDG Library Demo"
JBOSS_HOME=./target/jboss-eap-6.2
SERVER_DIR=$JBOSS_HOME/standalone/deployments/
SERVER_CONF=$JBOSS_HOME/standalone/configuration/
LIB_DIR=./support/lib
SRC_DIR=./installs
EAP=jboss-eap-6.2.0.zip
JDG=jboss-datagrid-6.3.0.ER7-eap-modules-library.zip


echo
echo "Setting up the JBoss Enterprise EAP 6 ${DEMO} environment..."
echo

# make some checks first before proceeding.	
if [[ -r $SRC_DIR/$EAP || -L $SRC_DIR/$EAP ]]; then
		echo EAP sources are present...
		echo
else
		echo Need to download $EAP package from the Customer Support Portal 
		echo and place it in the $SRC_DIR directory to proceed...
		echo
		exit
fi

# Create the target directory if it does not already exist.
if [ ! -x target ]; then
		echo "  - creating the target directory..."
		echo
		mkdir target
else
		echo "  - detected target directory, moving on..."
		echo
fi

# Move the old JBoss instance, if it exists, to the OLD position.
if [ -x $JBOSS_HOME ]; then
		echo "  - existing JBoss Enterprise EAP 6 detected..."
		echo
		echo "  - moving existing JBoss Enterprise EAP 6 aside..."
		echo
		rm -rf $JBOSS_HOME.OLD
		mv $JBOSS_HOME $JBOSS_HOME.OLD

		# Unzip the JBoss EAP instance.
		echo Unpacking JBoss Enterprise EAP 6...
		echo
		unzip -q -d target $SRC_DIR/$EAP
else
		# Unzip the JBoss EAP instance.
		echo Unpacking new JBoss Enterprise EAP 6...
		echo
		unzip -q -d target $SRC_DIR/$EAP
fi

echo "Adding admin user"
$JBOSS_HOME/bin/add-user.sh -g admin -u admin -p admin-123 -s

if [[ -r $SRC_DIR/$JDG || -L $SRC_DIR/$JDG ]]; then
		echo "JDG sources are present..."
		echo "Adding JBoss Data Grid Modules to EAP"
		tmpdir=`mktemp -d XXXXXXXX`
		unzip -q -d ${tmpdir} ${SRC_DIR}/${JDG}
		cp -R ${tmpdir}/jboss-datagrid-6.3.0-eap-modules-library/modules/* $JBOSS_HOME/modules/
		rm -rf  ${tmpdir} 
else
		echo Need to download $JDG package from the Customer Support Portal 
		echo and place it in the $SRC_DIR directory to proceed...
		echo
		exit
fi



echo "Done setting up environment"