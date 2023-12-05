type OscarProps = {
  children: React.ReactNode;
};

export const Oscar = (props: OscarProps) => {
  return (
    <div>
      <h1>{props.children}</h1>
    </div>
  );
};
